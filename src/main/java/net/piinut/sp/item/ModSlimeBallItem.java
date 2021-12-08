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
import net.piinut.sp.Main;
import net.piinut.sp.enchantment.ModEnchantmentRegistry;
import net.piinut.sp.entitiy.SlimeBallEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class ModSlimeBallItem extends Item {

    public static final String STICKINESS_KEY = "Stickiness";
    public static final int MAX_STICKINESS = 3;
    public static final int MIN_STICKINESS = 1;

    public ModSlimeBallItem(Settings settings) {
        super(settings);
    }

    private static int constrainStickiness(int stickiness){
        if(stickiness < MIN_STICKINESS){
            return MIN_STICKINESS;
        }
        if(stickiness > MAX_STICKINESS){
            return MAX_STICKINESS;
        }
        return stickiness;
    }

    public static void setStickiness(ItemStack stack, int stickiness){
        if(stack.isOf(ModItemRegistry.SLIME_BALL)){
            NbtCompound compound = stack.getOrCreateNbt();
            if(compound != null){
                compound.putInt(STICKINESS_KEY,constrainStickiness(stickiness));
                stack.writeNbt(compound);
            }
        }
    }

    public static int getStickiness(ItemStack stack){
        if(stack.isOf(ModItemRegistry.SLIME_BALL)){
            NbtCompound compound = stack.getOrCreateNbt();
            if(compound != null && compound.contains(STICKINESS_KEY)){
                return compound.getInt(STICKINESS_KEY);
            }
        }
        return MIN_STICKINESS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.6F);
		user.getItemCooldownManager().set(this, 10);
        if (!world.isClient) {
            SlimeBallEntity slimeballEntity = new SlimeBallEntity(world, user, getStickiness(itemStack));
            slimeballEntity.setItem(itemStack);
            slimeballEntity.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1F, 0F);
            world.spawnEntity(slimeballEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(new TranslatableText("item.minecraft.slime_ball.tooltip.stickiness").append(" ").append(Integer.toString(getStickiness(stack))).formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("item.minecraft.slime_ball.tooltip.description").formatted(Formatting.GRAY));
    }
}
