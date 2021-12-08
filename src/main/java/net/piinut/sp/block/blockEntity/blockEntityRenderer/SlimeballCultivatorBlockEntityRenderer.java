package net.piinut.sp.block.blockEntity.blockEntityRenderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import net.piinut.sp.Main;
import net.piinut.sp.block.blockEntity.SlimeballCultivatorBlockEntity;

@Environment(EnvType.CLIENT)
public class SlimeballCultivatorBlockEntityRenderer implements BlockEntityRenderer<SlimeballCultivatorBlockEntity> {

    //private static final Identifier TEXTURE = new Identifier(Main.MODID, "textures/block/slimeball_cultivator_top_layer");

    public SlimeballCultivatorBlockEntityRenderer(BlockEntityRendererFactory.Context ctx){
        super();
    }

    @Override
    public void render(SlimeballCultivatorBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        ItemStack itemStack = entity.getStack(0);
        ItemStack itemStack1 = entity.getStack(1);
        float r = ((float)entity.getTime())/SlimeballCultivatorBlockEntity.time_cap;
        float offset = 0.1f;
        float partialOffset = r*offset;
        Vec3f centerPos = new Vec3f(0.5f, 0.5f, -0.75f);
        float scale = 0.375f;

        if(!itemStack.isEmpty()){
            if(itemStack1.isEmpty()){
                matrices.push();
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0F));
                matrices.translate(centerPos.getX()+partialOffset, centerPos.getY()+partialOffset, centerPos.getZ());
                matrices.scale(scale, scale, scale);
                MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers, 0);
                matrices.pop();

                matrices.push();
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0F));
                matrices.translate(centerPos.getX()-partialOffset, centerPos.getY()-partialOffset, centerPos.getZ());
                matrices.scale(scale, scale, scale);
                MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers, 1);
                matrices.pop();
            }else{
                matrices.push();
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0F));
                matrices.translate(centerPos.getX()+offset, centerPos.getY()+offset, centerPos.getZ());
                matrices.scale(scale, scale, scale);
                MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack, ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers, 0);
                matrices.pop();

                matrices.push();
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90.0F));
                matrices.translate(centerPos.getX()-offset, centerPos.getY()-offset, centerPos.getZ());
                matrices.scale(scale, scale, scale);
                MinecraftClient.getInstance().getItemRenderer().renderItem(itemStack1, ModelTransformation.Mode.FIXED, light, overlay, matrices, vertexConsumers, 1);
                matrices.pop();
            }

        }


    }

}
