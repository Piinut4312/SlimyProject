package net.piinut.sp.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.piinut.sp.enchantment.ModEnchantmentRegistry;
import net.piinut.sp.entitiy.ExplodingMagmaCreamEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class ExplodingMagmaCreamItem extends Item {

    public static final String POWER_KEY = "Power";
    public static final float MAX_POWER = 2.5f;
    public static final float MIN_POWER = 1.5f;

    public ExplodingMagmaCreamItem(Settings settings) {
        super(settings);
    }

    private static float constrainPower(float power){
        if(power < MIN_POWER){
            return MIN_POWER;
        }
        if(power > MAX_POWER){
            return MAX_POWER;
        }
        return power;
    }

    public static void setPower(ItemStack stack, float power){
        if(stack.isOf(ModItemRegistry.EXPLODING_MAGMA_CREAM)){
            NbtCompound compound = stack.getOrCreateNbt();
            if(compound != null){
                compound.putFloat(POWER_KEY,constrainPower(power));
                stack.writeNbt(compound);
            }
        }
    }

    public static float getPower(ItemStack stack){
        if(stack.isOf(ModItemRegistry.EXPLODING_MAGMA_CREAM)){
            NbtCompound compound = stack.getOrCreateNbt();
            if(compound != null && compound.contains(POWER_KEY)){
                return compound.getFloat(POWER_KEY);
            }
        }
        return MIN_POWER;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.6F);
        user.getItemCooldownManager().set(this, 20);
        if (!world.isClient) {
            ExplodingMagmaCreamEntity explodingMagmaCream = new ExplodingMagmaCreamEntity(world, user, getPower(itemStack));
            explodingMagmaCream.setItem(itemStack);
            explodingMagmaCream.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1F, 0F);
            world.spawnEntity(explodingMagmaCream);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    private static int getPowerLevel(ItemStack stack){
        float power = getPower(stack);
        return (int)(2*power-2);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("item.sp.exploding_magma_cream.tooltip.power").append(" ").append(Integer.toString(getPowerLevel(stack))).formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("item.sp.exploding_magma_cream.tooltip.description").formatted(Formatting.GRAY));
    }
}
