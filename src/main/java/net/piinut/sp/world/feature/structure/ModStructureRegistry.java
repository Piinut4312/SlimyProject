package net.piinut.sp.world.feature.structure;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.piinut.sp.Main;

public class ModStructureRegistry {

    public static final StructurePieceType SLIMESHROOM_HUT_PIECE_TYPE = SlimeshroomHutGenerator.Piece::new;
    public static final StructureFeature<DefaultFeatureConfig> SLIMESHROOM_HUT_STRUCTURE = new SlimeshroomHutFeature(DefaultFeatureConfig.CODEC);
    public static final ConfiguredStructureFeature<DefaultFeatureConfig, ? extends StructureFeature<DefaultFeatureConfig>> SLIMESHROOM_HUT = SLIMESHROOM_HUT_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);

    public static void registerAll(){
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Main.MODID, "slimeshroom_hut"), SLIMESHROOM_HUT_PIECE_TYPE);
        FabricStructureBuilder.create(new Identifier(Main.MODID, "slimeshroom_hut"), SLIMESHROOM_HUT_STRUCTURE).step(GenerationStep.Feature.SURFACE_STRUCTURES)
                .defaultConfig(32, 8, 12345)
                .adjustsSurface()
                .superflatFeature(SLIMESHROOM_HUT)
                .register();

        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier(Main.MODID, "slime_hut"), SLIMESHROOM_HUT);

    }

}
