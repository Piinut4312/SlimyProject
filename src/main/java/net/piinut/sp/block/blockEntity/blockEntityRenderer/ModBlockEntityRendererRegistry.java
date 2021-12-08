package net.piinut.sp.block.blockEntity.blockEntityRenderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.piinut.sp.block.blockEntity.ModBlockEntityRegistry;

@Environment(EnvType.CLIENT)
public class ModBlockEntityRendererRegistry {

    public static void registerAll(){
        BlockEntityRendererRegistry.register(ModBlockEntityRegistry.SLIMEBALL_CULTIVATOR_BLOCK_ENTITY
                , SlimeballCultivatorBlockEntityRenderer::new);
    }
}
