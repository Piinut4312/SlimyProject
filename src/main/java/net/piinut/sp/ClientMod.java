package net.piinut.sp;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.piinut.sp.block.ModBlockRegistry;
import net.piinut.sp.block.blockEntity.ModBlockEntityRegistry;
import net.piinut.sp.block.blockEntity.blockEntityRenderer.ModBlockEntityRendererRegistry;
import net.piinut.sp.entitiy.EntitySpawnPacket;
import net.piinut.sp.entitiy.renderer.ModEntityRendererRegistry;
import net.piinut.sp.item.ModItemRegistry;

import java.util.UUID;

@Environment(EnvType.CLIENT)
public class ClientMod implements net.fabricmc.api.ClientModInitializer {

    public static final Identifier PacketID = new Identifier(Main.MODID, "spawn_packet");

    public void receiveEntityPacket() {
        ClientSidePacketRegistry.INSTANCE.register(PacketID, (ctx, byteBuf) -> {
            EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity e = et.create(MinecraftClient.getInstance().world);
                if (e == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
                e.updateTrackedPosition(pos);
                e.setPos(pos.x, pos.y, pos.z);
                e.setPitch(pitch);
                e.setYaw(yaw);
                e.setId(entityId);
                e.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, e);
            });
        });
    }

    @Override
    public void onInitializeClient() {
        ModEntityRendererRegistry.registerAll();
        receiveEntityPacket();
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.SLIME_LAYER, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.MAGMA_CREAM_LAYER, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.GLOWING_SLIME_LAYER, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.GLOWING_SLIME_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.SLIMESHROOM_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.POTTED_SLIMESHROOM, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.ENDER_SLIME_LAYER, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.SOUL_SLIME_LAYER, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.LIVERWORT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.POTTED_LIVERWORT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.EXPOSED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.WEATHERED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.OXIDIZED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.WAXED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.WAXED_EXPOSED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.WAXED_WEATHERED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.WAXED_OXIDIZED_COPPER_CHAIN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.COPPER_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.COPPER_SOUL_LANTERN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.SLIMEBALL_CULTIVATOR, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.SLIME_MOLD, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.SLIME_MOLD_MEMORY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.ELM_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlockRegistry.ELM_DOOR, RenderLayer.getCutout());
        ModBlockEntityRendererRegistry.registerAll();
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> 0x6A9813, ModBlockRegistry.ELM_LEAVES);
    }

}
