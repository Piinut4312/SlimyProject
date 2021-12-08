package net.piinut.sp.world.feature;

import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.piinut.sp.Main;

public class ModFeatureRegistry {

    public static final Feature<HugeMushroomFeatureConfig> HUGE_SLIMESHROOM = new HugeSlimeshroomFeature(HugeMushroomFeatureConfig.CODEC);
    public static final Feature<DefaultFeatureConfig> SLIME_LAYER_PATCH = new SlimeLayerPatchFeature(DefaultFeatureConfig.CODEC);

    private static void register(Feature<?> feature, String id){
        Registry.register(Registry.FEATURE, new Identifier(Main.MODID, id), feature);
    }

    public static void registerAll(){
        register(HUGE_SLIMESHROOM, "huge_slimeshroom");
        register(SLIME_LAYER_PATCH, "slime_layer_patch");
    }

}
