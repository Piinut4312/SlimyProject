package net.piinut.sp.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.piinut.sp.entitiy.ModEntityRegistry;
import net.piinut.sp.world.feature.ModConfiguredFeatureRegistry;
import net.piinut.sp.world.feature.structure.ModStructureRegistry;

public class ModBiomeCreator {

    protected static final SurfaceBuilder SLIMESHROOM_FOREST_SB = new SlimeshroomForestSurfaceBuilder(TernarySurfaceConfig.CODEC);

    protected static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> SLIMY_SURFACE_BUILDER = SLIMESHROOM_FOREST_SB.withConfig(SurfaceBuilder.GRASS_CONFIG);

    protected static final Biome SLIMESHROOM_FOREST = createSlimeshroomForest();

    private static Biome createSlimeshroomForest(){
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 3, 4, 4));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(ModEntityRegistry.AQUA_SLIME_ENTITY_ENTITY_TYPE, 1, 4, 4));
        DefaultBiomeFeatures.addCaveMobs(spawnSettings);
        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(SLIMY_SURFACE_BUILDER);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultLakes(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        DefaultBiomeFeatures.addMossyRocks(generationSettings);
        generationSettings.structureFeature(ModStructureRegistry.SLIMESHROOM_HUT);
        generationSettings.feature(GenerationStep.Feature.TOP_LAYER_MODIFICATION, ModConfiguredFeatureRegistry.SLIMESHROOM_FOREST_SLIME_LAYER);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredFeatureRegistry.SLIMESHROOM_FOREST_HUGE_SLIMESHROOM);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModConfiguredFeatureRegistry.SLIMESHROOM_FOREST_GRASS);
        DefaultBiomeFeatures.addSprings(generationSettings);

        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.NONE)
                .depth(0.2f)
                .scale(0.2f)
                .temperature(0.7f)
                .downfall(0.8f)
                .effects(new BiomeEffects.Builder()
                        .waterColor(0x3f76e4)
                        .waterFogColor(0x050533)
                        .fogColor(0xc0d8ff)
                        .skyColor(0x77adff)
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

}
