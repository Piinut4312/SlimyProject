package net.piinut.sp.entitiy.renderer;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.piinut.sp.Main;
import net.piinut.sp.entitiy.ModEntityRegistry;


@Environment(EnvType.CLIENT)
public class ModEntityRendererRegistry {

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
        registerProjectile(ModEntityRegistry.LIGHTNING_MAGMA_CREAM_ENTITY_TYPE);
        registerProjectile(ModEntityRegistry.AQUA_SLIME_BALL_ENTITY_TYPE);
    }

}
