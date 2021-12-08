package net.piinut.sp.mixin;

import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.piinut.sp.item.ModItemRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Items.class)
public class SlimeballMixin {

    @Inject(at = @At("TAIL"), method = "<clinit>()V")
    private static void register(CallbackInfo ci){
        int id = Registry.ITEM.getRawId(Items.SLIME_BALL);
        Identifier identifier = Registry.ITEM.getId(Items.SLIME_BALL);
        Registry.register(Registry.ITEM, id, identifier.toString(), ModItemRegistry.SLIME_BALL);

        id = Registry.ITEM.getRawId(Items.MAGMA_CREAM);
        identifier = Registry.ITEM.getId(Items.MAGMA_CREAM);
        Registry.register(Registry.ITEM, id, identifier.toString(), ModItemRegistry.MAGMA_CREAM);
    }

}
