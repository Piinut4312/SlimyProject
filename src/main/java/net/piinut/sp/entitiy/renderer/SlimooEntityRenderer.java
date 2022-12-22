package net.piinut.sp.entitiy.renderer;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.MooshroomMushroomFeatureRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.entity.passive.MooshroomEntity;
import net.minecraft.util.Identifier;
import net.piinut.sp.Main;
import net.piinut.sp.entitiy.SlimooEntity;

public class SlimooEntityRenderer extends MobEntityRenderer<SlimooEntity, CowEntityModel<SlimooEntity>> {

    private static final Identifier TEXTURE = new Identifier(Main.MODID, "textures/entity/slimoo/slimoo.png");
    private static final Identifier TEXTURE_ANGRY = new Identifier(Main.MODID, "textures/entity/slimoo/angry_slimoo.png");

    public SlimooEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CowEntityModel(context.getPart(EntityModelLayers.MOOSHROOM)), 0.7f);
        this.addFeature(new SlimooMushroomFeatureRenderer(this));
    }

    @Override
    public Identifier getTexture(SlimooEntity entity) {
        if(entity.hasAngerTime()){
            return TEXTURE_ANGRY;
        }else{
            return TEXTURE;
        }
    }
}
