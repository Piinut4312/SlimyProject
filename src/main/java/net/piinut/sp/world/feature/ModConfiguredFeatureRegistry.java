package net.piinut.sp.world.feature;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.HeightmapDecoratorConfig;
import net.minecraft.world.gen.decorator.WaterDepthThresholdDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.PineFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.piinut.sp.Main;
import net.piinut.sp.block.ModBlockRegistry;

public class ModConfiguredFeatureRegistry{

    public static final RandomPatchFeatureConfig SLIMESHROOM_FOREST_GRASS_CONFIG = (new RandomPatchFeatureConfig.Builder(new WeightedBlockStateProvider(pool().add(ModBlockRegistry.LIVERWORT.getDefaultState(), 8).add(Blocks.FERN.getDefaultState(), 4).add(ModBlockRegistry.SLIMESHROOM_PLANT.getDefaultState(), 2)), SimpleBlockPlacer.INSTANCE)).tries(32).build();

    public static ConfiguredFeature<?, ?> HUGE_SLIMESHROOM;
    public static ConfiguredFeature<?, ?> HARDENED_SLIMESHROOM;
    public static ConfiguredFeature<?, ?> SLIMESHROOM_FOREST_HUGE_SLIMESHROOM;
    public static ConfiguredFeature<?, ?> SLIMESHROOM_FOREST_HARDENED_SLIMESHROOM;
    public static ConfiguredFeature<?, ?> SLIME_LAYER;
    public static ConfiguredFeature<?, ?> SLIMESHROOM_FOREST_SLIME_LAYER;
    public static ConfiguredFeature<?, ?> PATCH_SLIMESHROOM_FOREST_GRASS = Feature.RANDOM_PATCH.configure(SLIMESHROOM_FOREST_GRASS_CONFIG);
    public static ConfiguredFeature<?, ?> SLIMESHROOM_FOREST_GRASS = (PATCH_SLIMESHROOM_FOREST_GRASS.decorate(Decorator.HEIGHTMAP_SPREAD_DOUBLE.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING)).spreadHorizontally()).applyChance(4)).repeat(8);
    public static ConfiguredFeature<?, ?> ELM_TREE = Feature.TREE
            .configure(new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(ModBlockRegistry.ELM_LOG.getDefaultState()),
                    new StraightTrunkPlacer(6, 4, 0),
                    new SimpleBlockStateProvider(ModBlockRegistry.ELM_LEAVES.getDefaultState()),
                    new SimpleBlockStateProvider(ModBlockRegistry.ELM_SAPLING.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1)
            ).build());
    public static ConfiguredFeature<?, ?> ELM_FOREST_VEGETATION;
    public static ConfiguredFeature<?, ?> SLIME_MOLD = ModFeatureRegistry.SLIME_MOLD.configure(new GlowLichenFeatureConfig(60, true, false, true, 0.75f, ImmutableList.of(Blocks.MOSSY_COBBLESTONE.getDefaultState(), Blocks.STONE.getDefaultState()))).spreadHorizontally().uniformRange(YOffset.fixed(48), YOffset.fixed(76)).repeat(UniformIntProvider.create(40, 60));

    static DataPool.Builder<BlockState> pool() {
        return DataPool.builder();
    }

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Main.MODID, id), configuredFeature);
    }

    public static void registerAll() {
        HUGE_SLIMESHROOM = register("huge_slimeshroom", ModFeatureRegistry.HUGE_SLIMESHROOM.configure(new HugeMushroomFeatureConfig(new SimpleBlockStateProvider(ModBlockRegistry.SLIMESHROOM_BLOCK.getDefaultState()), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 3)));
        HARDENED_SLIMESHROOM = register("hardened_slimeshroom", ModFeatureRegistry.HARDENED_SLIMESHROOM.configure(new HugeMushroomFeatureConfig(new SimpleBlockStateProvider(ModBlockRegistry.HARDENED_SLIME_BLOCK.getDefaultState()), new SimpleBlockStateProvider(Blocks.MUSHROOM_STEM.getDefaultState()), 1)));
        SLIMESHROOM_FOREST_HUGE_SLIMESHROOM = register("slimeshroom_forest_huge_slimeshroom", HUGE_SLIMESHROOM.decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(12))));
        SLIMESHROOM_FOREST_HARDENED_SLIMESHROOM = register("slimeshroom_forest_hardened_slimeshroom", HARDENED_SLIMESHROOM.decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(6))));
        SLIME_LAYER = register("slime_layer", ModFeatureRegistry.SLIME_LAYER_PATCH.configure(FeatureConfig.DEFAULT));
        SLIMESHROOM_FOREST_SLIME_LAYER = register("slimeshroom_forest_slime_layer", SLIME_LAYER.decorate(Decorator.COUNT_MULTILAYER.configure(new CountConfig(10))));
        register("patch_slimeshroom_forest_grass", PATCH_SLIMESHROOM_FOREST_GRASS);
        register("slimeshroom_forest_grass", SLIMESHROOM_FOREST_GRASS);
        register("elm_tree", ELM_TREE);
        ELM_FOREST_VEGETATION = register("elm_forest_vegetation"
                , Feature.RANDOM_SELECTOR.configure(
                                new RandomFeatureConfig(ImmutableList.of(
                                        ELM_TREE.withChance(0.66f)
                                        , ConfiguredFeatures.BIRCH.withChance(0.2F)
                                        , ConfiguredFeatures.BIRCH_TALL.withChance(0.1f))
                                        , ConfiguredFeatures.BIRCH_BEES_002))
                        .decorate(((Decorator.HEIGHTMAP.configure(
                                        new HeightmapDecoratorConfig(
                                                Heightmap.Type.OCEAN_FLOOR))
                                .decorate(Decorator.WATER_DEPTH_THRESHOLD
                                        .configure(new WaterDepthThresholdDecoratorConfig(0))))).spreadHorizontally())
                        .decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(12, 0.1f, 1))));
        register("slime_mold", SLIME_MOLD);
    }

}
