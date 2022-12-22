package net.piinut.sp.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.piinut.sp.block.AbstractSlimeLayerBlock;
import net.piinut.sp.entitiy.ModEntityRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class LayerBlockStickinessMixin {

    @Inject(method = "getVelocityMultiplier()F", at = @At("HEAD"), cancellable = true)
    private void injectedMethod(CallbackInfoReturnable<Float> cir){
        Entity entity = ((Entity)(Object)this);
        BlockState blockState = entity.world.getBlockState(entity.getBlockPos());
        if(blockState.getBlock() instanceof AbstractSlimeLayerBlock && !entity.getType().equals(ModEntityRegistry.SLIMOO_ENTITY_ENTITY_TYPE)){
            int level = blockState.get(AbstractSlimeLayerBlock.STICKINESS);
            cir.setReturnValue((float) (0.56-0.16*level));
        }
    }

}
