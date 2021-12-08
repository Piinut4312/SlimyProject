package net.piinut.sp.mixin;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.piinut.sp.Main;
import net.piinut.sp.enchantment.ModEnchantmentRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(Block.class)
public abstract class BounceMixin {

    @Inject(method = "onEntityLand(Lnet/minecraft/world/BlockView;Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    private void injectedMethod(BlockView world, Entity entity, CallbackInfo ci){
        if(entity instanceof LivingEntity){
            ItemStack itemStack = ((LivingEntity)entity).getEquippedStack(EquipmentSlot.FEET);
            if(EnchantmentHelper.getLevel(ModEnchantmentRegistry.BOUNCE, itemStack) > 0 && !entity.bypassesLandingEffects()){
                Vec3d vec3d = entity.getVelocity();
                if (vec3d.y < 0.0D) {
                    double d = 0.8D;
                    entity.setVelocity(vec3d.x, -vec3d.y * d, vec3d.z);
                }
                ci.cancel();
            }
        }
    }

}
