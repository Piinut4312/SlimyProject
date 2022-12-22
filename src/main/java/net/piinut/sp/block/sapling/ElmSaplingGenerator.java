package net.piinut.sp.block.sapling;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.piinut.sp.world.feature.ModConfiguredFeatureRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ElmSaplingGenerator extends SaplingGenerator {

    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random random, boolean bees) {
        return (ConfiguredFeature<TreeFeatureConfig, ?>) ModConfiguredFeatureRegistry.ELM_TREE;
    }
}
