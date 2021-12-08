package net.piinut.sp.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.HeightmapDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.piinut.sp.Main;
import net.piinut.sp.block.ModBlockRegistry;

public class ModConfiguredFeatureRegistry{

    public static final RandomPatchFeatureConfig SLIMESHROOM_FOREST_GRASS_CONFIG = (new RandomPatchFeatureConfig.Builder(new WeightedBlockStateProvider(pool().add(ModBlockRegistry.LIVERWORT.getDefaultState(), 8).add(ModBlockRegistry.SLIMESHROOM_PLANT.getDefaultState(), 1)), SimpleBlockPlacer.INSTANCE)).tries(32).build();

    public static ConfiguredFeature<?, ?> HUGE_SLIMESHROOM;
    public static ConfiguredFeature<?, ?> PATCH_SLIMESHROOM = Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlockRegistry.SLIMESHROOM_PLANT.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(64).cannotProject().build());
    public static ConfiguredFeature<?, ?> SLIMESHROOM_NORMAL = (PATCH_SLIMESHROOM.decorate(Decorator.HEIGHTMAP_SPREAD_DOUBLE.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING)).spreadHorizontally()).applyChance(4)).repeat(6);
    public static ConfiguredFeature<?, ?> SLIMESHROOM_FOREST_HUGE_SLIMESHROOM;
    public static ConfiguredFeature<?, ?> SLIME_LAYER;
    public static ConfiguredFeature<?, ?> SLIMESHROOM_FOREST_SLIME_LAYER;
    public static ConfiguredFeature<?, ?> PATCH_LIVERWORT = Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlockRegistry.LIVERWORT.getDefaultState()), new SimpleBlockPlacer())).tries(64).cannotProject().build());
    public static ConfiguredFeature<?, ?> LIVERWORT_NORMAL = (PATCH_LIVERWORT.decorate(Decorator.HEIGHTMAP_SPREAD_DOUBLE.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING)).spreadHorizontally()).applyChance(4)).repeat(8);
    public static ConfiguredFeature<?, ?> PATCH_SLIMESHROOM_FOREST_GRASS = Feature.RANDOM_PATCH.configure(SLIMESHROOM_FOREST_GRASS_CONFIG);
    public static ConfiguredFeature<?, ?> SLIMESHROOM_FOREST_GRASS = (PATCH_SLIMESHROOM_FOREST_GRASS.decorate(Decorator.HEIGHTMAP_SPREAD_DOUBLE.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING)).spreadHorizontally()).applyChance(4)).repeat(8);

    static DataPool.Builder<BlockState> pool() {
        return DataPool.builder();
    }

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Main.MODID, id), configuredFeature);
    }

    public static void registerAll(){
        HUGE_SLIMESHROOM = register("huge_slimeshroom", ModFeatureRegistry.HUGE_SLIMESHROOM.configure(new HugeMushroomFeatureConfig(new SimpleBlockStateProvider(ModBlockRegistry.SLIMESHROOM_BLOCK.getDefaultState()), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 3)));
        register("patch_slimeshroom", PATCH_SLIMESHROOM);
        register("slimeshroom_normal", SLIMESHROOM_NORMAL);
        SLIMESHROOM_FOREST_HUGE_SLIMESHROOM = register("slimeshroom_forest_huge_slimeshroom", HUGE_SLIMESHROOM.decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(32))));
        SLIME_LAYER = register("slime_layer", ModFeatureRegistry.SLIME_LAYER_PATCH.configure(FeatureConfig.DEFAULT));
        SLIMESHROOM_FOREST_SLIME_LAYER = register("slimeshroom_forest_slime_layer", SLIME_LAYER.decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(10))));
        register("patch_liverwort", PATCH_LIVERWORT);
        register("liverwort_normal", LIVERWORT_NORMAL);
        register("patch_slimeshroom_forest_grass", PATCH_SLIMESHROOM_FOREST_GRASS);
        register("slimeshroom_forest_grass", SLIMESHROOM_FOREST_GRASS);
    }
}
