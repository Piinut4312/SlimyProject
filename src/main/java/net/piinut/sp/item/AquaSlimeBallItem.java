package net.piinut.sp.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.piinut.sp.entitiy.AquaSlimeBallEntity;
import net.piinut.sp.entitiy.EnderSlimeBallEntity;

public class AquaSlimeBallItem extends Item {
    public AquaSlimeBallItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.6F);
        user.getItemCooldownManager().set(this, 10);
        if (!world.isClient) {
            AquaSlimeBallEntity aquaSlimeBall = new AquaSlimeBallEntity(world, user);
            aquaSlimeBall.setItem(itemStack);
            aquaSlimeBall.setProperties(user, user.getPitch(), user.getYaw(), 0.0F, 1F, 0F);
            world.spawnEntity(aquaSlimeBall);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }
}
