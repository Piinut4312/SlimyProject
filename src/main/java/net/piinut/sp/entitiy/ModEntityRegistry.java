package net.piinut.sp.entitiy;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.piinut.sp.Main;

public class ModEntityRegistry {

    public static EntityType<SlimeBallEntity> SLIME_BALL_ENTITY_TYPE;
    public static EntityType<MagmaCreamEntity> MAGMA_CREAM_ENTITY_TYPE;
    public static EntityType<GlowingSlimeBallEntity> GLOWING_SLIME_BALL_ENTITY_TYPE;
    public static EntityType<ExplodingMagmaCreamEntity> EXPLODING_MAGMA_CREAM_ENTITY_TYPE;
    public static EntityType<HardenedSlimeBallEntity> HARDENED_SLIME_BALL_ENTITY_TYPE;
    public static EntityType<BlazingMagmaCreamEntity> BLAZING_MAGMA_CREAM_ENTITY_TYPE;
    public static EntityType<EnderSlimeBallEntity> ENDER_SLIME_BALL_ENTITY_TYPE;
    public static EntityType<LightningMagmaCreamEntity> LIGHTNING_MAGMA_CREAM_ENTITY_TYPE;

    private static <T extends Entity> EntityType<T> registerProjectile(String id, EntityType.EntityFactory<T> factory){
        return Registry.register(Registry.ENTITY_TYPE, new Identifier(Main.MODID, id), FabricEntityTypeBuilder.create(SpawnGroup.MISC, factory)
                .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                .trackRangeBlocks(4).trackedUpdateRate(10)
                .build());
    }


    public static void registerAll(){
        SLIME_BALL_ENTITY_TYPE = registerProjectile("slime_ball", SlimeBallEntity::new);
        MAGMA_CREAM_ENTITY_TYPE = registerProjectile("magma_cream", MagmaCreamEntity::new);
        GLOWING_SLIME_BALL_ENTITY_TYPE = registerProjectile("glowing_slime_ball", GlowingSlimeBallEntity::new);
        EXPLODING_MAGMA_CREAM_ENTITY_TYPE = registerProjectile("exploding_magma_cream", ExplodingMagmaCreamEntity::new);
        HARDENED_SLIME_BALL_ENTITY_TYPE = registerProjectile("hardened_slime_ball", HardenedSlimeBallEntity::new);
        BLAZING_MAGMA_CREAM_ENTITY_TYPE = registerProjectile("blazing_magma_cream", BlazingMagmaCreamEntity::new);
        ENDER_SLIME_BALL_ENTITY_TYPE = registerProjectile("ender_slime_ball", EnderSlimeBallEntity::new);
        LIGHTNING_MAGMA_CREAM_ENTITY_TYPE = registerProjectile("lightning_magma_cream", LightningMagmaCreamEntity::new);
    }

}
