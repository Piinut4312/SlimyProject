package net.piinut.sp.world.biome;

import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.piinut.sp.Main;

public class ModBiomeRegistry {

    public static final RegistryKey<Biome> SLIMESHROOM_KEY = getBiomeKey("slimeshroom_forest");

    private static void registerSurfaceBuilder(String id, SurfaceBuilder surfaceBuilder){
        Registry.register(Registry.SURFACE_BUILDER, new Identifier(Main.MODID, id), surfaceBuilder);
    }

    private static void registerConfiguredSurfaceBuilder(String id, ConfiguredSurfaceBuilder configuredSurfaceBuilder){
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(Main.MODID, id), configuredSurfaceBuilder);
    }

    private static RegistryKey<Biome> getBiomeKey(String name) {
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(Main.MODID, name));
    }

    private static void registerBiome(Biome biome){
        Registry.register(BuiltinRegistries.BIOME, SLIMESHROOM_KEY.getValue(), biome);
    }

    public static void registerAll(){
        registerSurfaceBuilder("slimeshroom_forest", ModBiomeCreator.SLIMESHROOM_FOREST_SB);
        registerConfiguredSurfaceBuilder("slimy_surface", ModBiomeCreator.SLIMY_SURFACE_BUILDER);
        registerBiome(ModBiomeCreator.SLIMESHROOM_FOREST);
        OverworldBiomes.addContinentalBiome(SLIMESHROOM_KEY, OverworldClimate.TEMPERATE, 0.5D);
    }

}
