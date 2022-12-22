package net.piinut.sp.entitiy.renderer;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;
import net.piinut.sp.block.ModBlockRegistry;
import net.piinut.sp.entitiy.SlimooEntity;

public class SlimooMushroomFeatureRenderer <T extends SlimooEntity>
        extends FeatureRenderer<T, CowEntityModel<T>> {

    public SlimooMushroomFeatureRenderer(FeatureRendererContext<T, CowEntityModel<T>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.isBaby()) {
            return;
        }
        MinecraftClient minecraftClient = MinecraftClient.getInstance();
        boolean bl = minecraftClient.hasOutline(entity) && entity.isInvisible();
        if (entity.isInvisible() && !bl) {
            return;
        }
        BlockRenderManager blockRenderManager = minecraftClient.getBlockRenderManager();
        BlockState blockState = ModBlockRegistry.SLIMESHROOM_PLANT.getDefaultState();
        int m = LivingEntityRenderer.getOverlay(entity, 0.0f);
        BakedModel bakedModel = blockRenderManager.getModel(blockState);
        matrices.push();
        matrices.translate(0.2f, -0.35f, 0.5);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0f));
        matrices.scale(-0.6f, -0.6f, 0.6f);
        matrices.translate(-0.5, -0.9, -0.5);
        this.renderMushroom(matrices, vertexConsumers, light, bl, blockRenderManager, blockState, m, bakedModel);
        matrices.pop();
        matrices.push();
        matrices.translate(0.2f, -0.35f, 0.5);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(42.0f));
        matrices.translate(0.1f, 0.0, -0.6f);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-48.0f));
        matrices.scale(-0.6f, -0.6f, 0.6f);
        matrices.translate(-0.5, -0.9, -0.5);
        this.renderMushroom(matrices, vertexConsumers, light, bl, blockRenderManager, blockState, m, bakedModel);
        matrices.pop();
        matrices.push();
        this.getContextModel().getHead().rotate(matrices);
        matrices.translate(0.0, -0.7f, -0.2f);
        matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-78.0f));
        matrices.scale(-0.6f, -0.6f, 0.6f);
        matrices.translate(-0.5, -0.9, -0.5);
        this.renderMushroom(matrices, vertexConsumers, light, bl, blockRenderManager, blockState, m, bakedModel);
        matrices.pop();
    }

    private void renderMushroom(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, boolean renderAsModel, BlockRenderManager blockRenderManager, BlockState mushroomState, int overlay, BakedModel mushroomModel) {
        if (renderAsModel) {
            blockRenderManager.getModelRenderer().render(matrices.peek(), vertexConsumers.getBuffer(RenderLayer.getOutline(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)), mushroomState, mushroomModel, 0.0f, 0.0f, 0.0f, light, overlay);
        } else {
            blockRenderManager.renderBlockAsEntity(mushroomState, matrices, vertexConsumers, light, overlay);
        }
    }

}
