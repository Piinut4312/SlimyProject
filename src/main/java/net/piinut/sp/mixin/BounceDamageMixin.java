package net.piinut.sp.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.piinut.sp.Main;
import net.piinut.sp.enchantment.ModEnchantmentRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BounceDamageMixin {

    @Inject(method = "onLandedUpon(Lnet/minecraft/world/World;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/Entity;F)V", at = @At("HEAD"), cancellable = true)
    private void injectedMethod(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci){
        if(entity instanceof LivingEntity){
            ItemStack itemStack = ((LivingEntity)entity).getEquippedStack(EquipmentSlot.FEET);
            if(EnchantmentHelper.getLevel(ModEnchantmentRegistry.BOUNCE, itemStack) > 0 && !entity.bypassesLandingEffects()){
                entity.handleFallDamage(fallDistance, 0.0F, DamageSource.FALL);
                ci.cancel();
            }
        }
    }

}
