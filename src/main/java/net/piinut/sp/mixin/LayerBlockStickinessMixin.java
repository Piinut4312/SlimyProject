package net.piinut.sp.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SlimeEntity;
import net.piinut.sp.block.LayerBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class LayerBlockStickinessMixin {

    @Inject(method = "getVelocityMultiplier()F", at = @At("HEAD"), cancellable = true)
    private void injectedMethod(CallbackInfoReturnable<Float> cir){
        BlockState blockState = ((Entity)(Object)this).world.getBlockState(((Entity)(Object)this).getBlockPos());
        if(blockState.getBlock() instanceof LayerBlock){
            int level = blockState.get(LayerBlock.STICKINESS);
            cir.setReturnValue((float) (0.56-0.16*level));
        }
    }

}
