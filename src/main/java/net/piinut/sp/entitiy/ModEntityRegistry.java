package net.piinut.sp.entitiy;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.piinut.sp.Main;

public class ModEntityRegistry {

    public static EntityType<SlimeBallEntity> SLIME_BALL_ENTITY_TYPE;
    public static EntityType<MagmaCreamEntity> MAGMA_CREAM_ENTITY_TYPE;
    public static EntityType<GlowingSlimeBallEntity> GLOWING_SLIME_BALL_ENTITY_TYPE;
    public static EntityType<ExplodingMagmaCreamEntity> EXPLODING_MAGMA_CREAM_ENTITY_TYPE;
    public static EntityType<HardenedSlimeBallEntity> HARDENED_SLIME_BALL_ENTITY_TYPE;
    public static EntityType<BlazingMagmaCreamEntity> BLAZING_MAGMA_CREAM_ENTITY_TYPE;
    public static EntityType<EnderSlimeBallEntity> ENDER_SLIME_BALL_ENTITY_TYPE;
    public static EntityType<SoulSlimeBallEntity> SOUL_SLIME_BALL_ENTITY_TYPE;
    public static EntityType<LightningMagmaCreamEntity> LIGHTNING_MAGMA_CREAM_ENTITY_TYPE;
    public static EntityType<AquaSlimeBallEntity> AQUA_SLIME_BALL_ENTITY_TYPE;

    public static final EntityType<AquaSlimeEntity> AQUA_SLIME_ENTITY_ENTITY_TYPE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Main.MODID, "aqua_slime"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AquaSlimeEntity::new).dimensions(EntityDimensions.changing(2.04f, 2.04f)).trackRangeBlocks(10).build()
    );

    public static final EntityType<SlimooEntity> SLIMOO_ENTITY_ENTITY_TYPE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Main.MODID, "slimoo"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SlimooEntity::new).dimensions(EntityDimensions.changing(0.9f, 1.4f)).trackRangeBlocks(10).build()
    );

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
        SOUL_SLIME_BALL_ENTITY_TYPE = registerProjectile("soul_slime_ball", SoulSlimeBallEntity::new);
        LIGHTNING_MAGMA_CREAM_ENTITY_TYPE = registerProjectile("lightning_magma_cream", LightningMagmaCreamEntity::new);
        AQUA_SLIME_BALL_ENTITY_TYPE = registerProjectile("aqua_slime_ball", AquaSlimeBallEntity::new);

        SpawnRestrictionAccessor.callRegister(AQUA_SLIME_ENTITY_ENTITY_TYPE, SpawnRestriction.Location.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, AquaSlimeEntity::canAquaSlimeSpawn);
        SpawnRestrictionAccessor.callRegister(SLIMOO_ENTITY_ENTITY_TYPE, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SlimooEntity::canSpawn);
        FabricDefaultAttributeRegistry.register(AQUA_SLIME_ENTITY_ENTITY_TYPE, HostileEntity.createHostileAttributes());
        FabricDefaultAttributeRegistry.register(SLIMOO_ENTITY_ENTITY_TYPE, SlimooEntity.createSlimooAttributes());
    }

}
