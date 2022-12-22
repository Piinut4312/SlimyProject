package net.piinut.sp.entitiy.renderer;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.CowEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.piinut.sp.Main;
import net.piinut.sp.entitiy.ModEntityRegistry;
import net.piinut.sp.entitiy.model.AquaSlimeEntityModel;

@Environment(EnvType.CLIENT)
public class ModEntityRendererRegistry {

    public static final EntityModelLayer AQUA_SLIME_LAYER = new EntityModelLayer(new Identifier(Main.MODID, "aqua_slime"), "main");
    public static final EntityModelLayer AQUA_SLIME_OUTER_LAYER = new EntityModelLayer(new Identifier(Main.MODID, "aqua_slime"), "outer");
    public static final EntityModelLayer SLIMOO_LAYER = new EntityModelLayer(new Identifier(Main.MODID, "slimoo"), "main");

    private static <E extends Entity> void registerProjectile(EntityType<? extends E> entityType){
        EntityRendererRegistry.register(entityType, (context) -> new FlyingItemEntityRenderer(context));
    }

    public static void registerAll(){
        registerProjectile(ModEntityRegistry.SLIME_BALL_ENTITY_TYPE);
        registerProjectile(ModEntityRegistry.MAGMA_CREAM_ENTITY_TYPE);
        registerProjectile(ModEntityRegistry.GLOWING_SLIME_BALL_ENTITY_TYPE);
        registerProjectile(ModEntityRegistry.EXPLODING_MAGMA_CREAM_ENTITY_TYPE);
        registerProjectile(ModEntityRegistry.HARDENED_SLIME_BALL_ENTITY_TYPE);
        registerProjectile(ModEntityRegistry.BLAZING_MAGMA_CREAM_ENTITY_TYPE);
        registerProjectile(ModEntityRegistry.ENDER_SLIME_BALL_ENTITY_TYPE);
        registerProjectile(ModEntityRegistry.SOUL_SLIME_BALL_ENTITY_TYPE);
        registerProjectile(ModEntityRegistry.LIGHTNING_MAGMA_CREAM_ENTITY_TYPE);
        registerProjectile(ModEntityRegistry.AQUA_SLIME_BALL_ENTITY_TYPE);

        EntityRendererRegistry.register(ModEntityRegistry.AQUA_SLIME_ENTITY_ENTITY_TYPE, AquaSlimeEntityRenderer::new);
        EntityRendererRegistry.register(ModEntityRegistry.SLIMOO_ENTITY_ENTITY_TYPE, SlimooEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(AQUA_SLIME_LAYER, AquaSlimeEntityModel::getInnerTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(AQUA_SLIME_OUTER_LAYER, AquaSlimeEntityModel::getOuterTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(SLIMOO_LAYER, CowEntityModel::getTexturedModelData);

    }

}
