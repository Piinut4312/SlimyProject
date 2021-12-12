package net.piinut.sp.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.piinut.sp.block.blockEntity.ModBlockEntityRegistry;
import net.piinut.sp.block.blockEntity.SlimeballCultivatorBlockEntity;
import net.piinut.sp.item.ModItemTags;

public class SlimeballCultivatorBlock extends BlockWithEntity {

    public static final IntProperty WATER = IntProperty.of("water", 0, 6);

    public static final IntProperty NUTRIENT = IntProperty.of("nutrient", 0, 12);

    public VoxelShape makeShape(){

        VoxelShape shape = VoxelShapes.empty();
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0, 0, 0, 1, 0.5, 1), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.1875, 0.6875, 0.1875, 0.8125, 0.75, 0.8125), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.1875, 0.75, 0.1875, 0.8125, 0.875, 0.25), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.1875, 0.75, 0.75, 0.8125, 0.875, 0.8125), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.75, 0.75, 0.25, 0.8125, 0.875, 0.75), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.1875, 0.75, 0.25, 0.25, 0.875, 0.75), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0, 0, 0, 1, 0.5, 1), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.0625, 0.5, 0.4375, 0.1875, 0.875, 0.5625), BooleanBiFunction.OR);
        shape = VoxelShapes.combine(shape, VoxelShapes.cuboid(0.8125, 0.5, 0.4375, 0.9375, 0.875, 0.5625), BooleanBiFunction.OR);

        return shape;
    }

    protected SlimeballCultivatorBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getStateManager().getDefaultState().with(WATER, 0).with(NUTRIENT, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATER).add(NUTRIENT);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient()){
            return ActionResult.SUCCESS;
        }

        SlimeballCultivatorBlockEntity blockEntity = (SlimeballCultivatorBlockEntity) world.getBlockEntity(pos);
        ItemStack itemStack = player.getStackInHand(hand);
        if(!blockEntity.getStack(1).isEmpty()){
            ItemStack itemStack1 = blockEntity.getStack(1).copy();
            player.getInventory().offerOrDrop(itemStack1);
            blockEntity.removeStack(1);
            world.playSound(null, pos, SoundEvents.BLOCK_SLIME_BLOCK_HIT, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }else{
            if(ModItemTags.SLIMEBALL_CULTIVATOR_ACCEPTED.contains(itemStack.getItem()) && blockEntity.getStack(0).isEmpty()){
                ItemStack itemStack1 = itemStack.copy();
                itemStack1.setCount(1);
                blockEntity.setStack(0, itemStack1);
                if(!player.getAbilities().creativeMode){
                    itemStack.decrement(1);
                }
                world.playSound(null, pos, SoundEvents.BLOCK_SLIME_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }else if(ModItemTags.SLIMEBALL_CULTIVATOR_FOOD_SUPPLY.contains(itemStack.getItem()) && !blockEntity.isNutrientFull()){
                blockEntity.addNutrient(1);
                world.setBlockState(pos, state.with(NUTRIENT, blockEntity.getNutrient()));
                itemStack.decrement(1);
                world.playSound(null, pos, SoundEvents.BLOCK_COMPOSTER_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }else if(itemStack.isOf(Items.WATER_BUCKET) && !blockEntity.isLiquidFull()){
                blockEntity.addLiquid(3);
                ItemUsage.exchangeStack(itemStack, player, new ItemStack(Items.BUCKET));
                world.setBlockState(pos, state.with(WATER, blockEntity.getLiquid()));
                world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }else if(itemStack.isOf(Items.POTION) && PotionUtil.getPotion(itemStack) == Potions.WATER){
                blockEntity.addLiquid(1);
                ItemUsage.exchangeStack(itemStack, player, new ItemStack(Items.GLASS_BOTTLE));
                world.setBlockState(pos, state.with(WATER, blockEntity.getLiquid()));
                world.playSound(null, pos, SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }else if(!blockEntity.getStack(0).isEmpty()){
                player.getInventory().offerOrDrop(blockEntity.getStack(0).copy());
                blockEntity.removeStack(0);
                world.playSound(null, pos, SoundEvents.BLOCK_SLIME_BLOCK_HIT, SoundCategory.BLOCKS, 1.0f, 1.0f);
            }else{
                return ActionResult.PASS;
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SlimeballCultivatorBlockEntity) {
                ItemScatterer.spawn(world, pos, (SlimeballCultivatorBlockEntity)blockEntity);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SlimeballCultivatorBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, ModBlockEntityRegistry.SLIMEBALL_CULTIVATOR_BLOCK_ENTITY, SlimeballCultivatorBlockEntity::tick);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return makeShape();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return makeShape();
    }
}
