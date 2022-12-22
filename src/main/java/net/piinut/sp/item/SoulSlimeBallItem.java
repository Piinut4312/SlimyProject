package net.piinut.sp.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.piinut.sp.entitiy.GlowingSlimeBallEntity;
import net.piinut.sp.entitiy.SoulSlimeBallEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulSlimeBallItem extends Item {

    public SoulSlimeBallItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.6F);
        user.getItemCooldownManager().set(this, 10);
        if (!world.isClient) {
            SoulSlimeBallEntity soulSlimeBall = new SoulSlimeBallEntity(world, user);
            soulSlimeBall.setItem(itemStack);
            soulSlimeBall.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1F, 0F);
            world.spawnEntity(soulSlimeBall);
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
        tooltip.add(new TranslatableText("item.sp.soul_slime_ball.tooltip.description").formatted(Formatting.GRAY));
    }

}
