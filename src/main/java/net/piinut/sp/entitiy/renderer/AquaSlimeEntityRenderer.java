package net.piinut.sp.entitiy.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.SlimeEntityRenderer;
import net.minecraft.client.render.entity.feature.SlimeOverlayFeatureRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.piinut.sp.Main;
import net.piinut.sp.entitiy.AquaSlimeEntity;
import net.piinut.sp.entitiy.model.AquaSlimeEntityModel;

@Environment(EnvType.CLIENT)
public class AquaSlimeEntityRenderer extends MobEntityRenderer<AquaSlimeEntity, AquaSlimeEntityModel<AquaSlimeEntity>> {

    private static final Identifier TEXTURE = new Identifier(Main.MODID, "textures/entity/slime/aqua_slime.png");

    public AquaSlimeEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AquaSlimeEntityModel(context.getPart(EntityModelLayers.SLIME)), 0.25F);
        this.addFeature(new AquaSlimeOverlayFeatureRenderer(this, context.getModelLoader()));
    }

    @Override
    public Identifier getTexture(AquaSlimeEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(AquaSlimeEntity slimeEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        this.shadowRadius = 0.25F * (float)slimeEntity.getSize();
        super.render(slimeEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    protected void scale(AquaSlimeEntity slimeEntity, MatrixStack matrixStack, float f) {
        matrixStack.scale(0.999F, 0.999F, 0.999F);
        matrixStack.translate(0.0D, 0.0010000000474974513D, 0.0D);
        float h = (float)slimeEntity.getSize();
        float i = MathHelper.lerp(f, slimeEntity.lastStretch, slimeEntity.stretch) / (h * 0.5F + 1.0F);
        float j = 1.0F / (i + 1.0F);
        matrixStack.scale(j * h, 1.0F / j * h, j * h);
    }

}
