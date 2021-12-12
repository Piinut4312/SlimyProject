package net.piinut.sp.block.blockEntity;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.piinut.sp.block.SlimeballCultivatorBlock;
import net.piinut.sp.item.ModItemRegistry;
import net.piinut.sp.item.ModItemTags;
import org.jetbrains.annotations.Nullable;

public class SlimeballCultivatorBlockEntity extends BlockEntity implements ImplementedInventory, SidedInventory, BlockEntityClientSerializable {

    private static final String NUTRIENT_KEY = "Nutrient";
    private static final String LIQUID_KEY = "Liquid";
    private static final String TIME_KEY = "Time";
    private int nutrient;
    private int liquid;
    private int time;
    public static final int nutrient_cap = 12;
    public static final int liquid_cap = 6;
    public static int time_cap = 2000;

    public static final Object2IntMap<ItemConvertible> FOOD_CONSUME = new Object2IntOpenHashMap<>();

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(4, ItemStack.EMPTY);

    public SlimeballCultivatorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityRegistry.SLIMEBALL_CULTIVATOR_BLOCK_ENTITY, pos, state);
        this.nutrient = 0;
        this.liquid = 0;
        this.time = 0;
    }

    private static void registerFoodConsume(ItemConvertible item, int food){
        FOOD_CONSUME.put(item.asItem(), food);
    }

    static{
        FOOD_CONSUME.defaultReturnValue(1);
        registerFoodConsume(ModItemRegistry.SLIME_BALL, 1);
        registerFoodConsume(ModItemRegistry.GLOWING_SLIME_BALL, 3);
        registerFoodConsume(ModItemRegistry.ENDER_SLIME_BALL, 12);
        registerFoodConsume(ModItemRegistry.GOLDEN_SLIME_BALL, 12);
        registerFoodConsume(ModItemRegistry.AQUA_SLIME_BALL, 2);
    }

    public int getNutrient(){
        return this.nutrient;
    }

    public int getLiquid(){
        return this.liquid;
    }

    public int getTime(){
        return this.time;
    }

    public void addNutrient(int amount){
        this.nutrient = Math.min(this.nutrient+amount, nutrient_cap);
        this.sync();
    }

    public void addLiquid(int amount){
        this.liquid = Math.min(this.liquid+amount, liquid_cap);
        this.sync();
    }

    public void removeNutrient(int amount){
        this.nutrient = Math.max(0, this.nutrient-amount);
        this.sync();
    }

    public void removeLiquid(int amount){
        this.liquid = Math.max(0, this.liquid-amount);
        this.sync();
    }

    public void increaseTime(){
        this.time++;
        if(this.time > time_cap){
            this.time = time_cap;
        }
        this.sync();
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        ImplementedInventory.super.setStack(slot, stack);
        this.sync();
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack itemStack = ImplementedInventory.super.removeStack(slot);
        this.sync();
        return itemStack;
    }

    public boolean canProduceItem(){
        return this.time >= time_cap && this.getStack(1).isEmpty();
    }

    public boolean isNutrientFull(){
        return getNutrient() >= nutrient_cap;
    }

    public boolean isLiquidFull(){
        return getLiquid() >= liquid_cap;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        this.saveInitialChunkData(nbt);
        nbt.putInt(NUTRIENT_KEY, this.nutrient);
        nbt.putInt(LIQUID_KEY, this.liquid);
        nbt.putInt(TIME_KEY, this.time);
        Inventories.writeNbt(nbt, items, true);
        this.sync();
        return super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.items.clear();
        this.nutrient = nbt.getInt(NUTRIENT_KEY);
        this.liquid = nbt.getInt(LIQUID_KEY);
        this.time = nbt.getInt(TIME_KEY);
        Inventories.readNbt(nbt, items);
    }

    public static int getFoodConsume(ItemStack itemStack){
        return FOOD_CONSUME.getInt(itemStack.getItem());
    }

    public static void tick(World world, BlockPos pos, BlockState state, SlimeballCultivatorBlockEntity be){

        if(!world.isClient()){
            ItemStack itemStack = be.getStack(0);
            ItemStack itemStack1 = be.getStack(1);
            ItemStack itemStack2 = be.getStack(2);
            ItemStack itemStack3 = be.getStack(3);
            if(!itemStack2.isEmpty() && be.getNutrient() < nutrient_cap){
                be.removeStack(2);
                be.addNutrient(1);
                world.setBlockState(pos, state.with(SlimeballCultivatorBlock.NUTRIENT, be.getNutrient()));
                world.playSound(null, pos, SoundEvents.BLOCK_COMPOSTER_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                be.markDirty();
            }
            if(!itemStack3.isEmpty() && be.getLiquid() < liquid_cap){
                be.removeStack(3);
                be.addLiquid(2);
                world.setBlockState(pos, state.with(SlimeballCultivatorBlock.WATER, be.getLiquid()));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                be.markDirty();
            }
            if(!itemStack.isEmpty() && itemStack1.isEmpty()){
                if(be.getNutrient() >= getFoodConsume(itemStack) && be.getLiquid() > 0){
                    be.increaseTime();
                    if(be.canProduceItem()){
                        be.setStack(1, itemStack.copy());
                        if(FOOD_CONSUME.containsKey(itemStack.getItem())){
                            be.removeNutrient(getFoodConsume(itemStack));
                        }
                        be.removeLiquid(1);
                        be.time = 0;
                        world.setBlockState(pos, state.with(SlimeballCultivatorBlock.WATER, be.liquid).with(SlimeballCultivatorBlock.NUTRIENT, be.nutrient));
                    }
                    be.markDirty();
                }
            }else if(itemStack.isEmpty()){
                be.time = 0;
                be.markDirty();
            }

        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public int[] getAvailableSlots(Direction side) {
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        if(dir == Direction.UP){
            return false;
        }
        if(slot == 2){
            return ModItemTags.SLIMEBALL_CULTIVATOR_FOOD_SUPPLY.contains(stack.getItem()) && this.nutrient < nutrient_cap;
        }else if(slot == 3){
            return ModItemTags.SLIMEBALL_CULTIVATOR_LIQUID_SUPPLY.contains(stack.getItem()) && this.liquid < liquid_cap;
        }
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return dir == Direction.DOWN && slot == 1;
    }

    @Override
    public void fromClientTag(NbtCompound tag) {
        this.readNbt(tag);
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        return this.writeNbt(tag);
    }

    private void saveInitialChunkData(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.items, true);
    }
}
