package net.piinut.sp;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.piinut.sp.block.ModBlockRegistry;
import net.piinut.sp.block.blockEntity.ModBlockEntityRegistry;
import net.piinut.sp.enchantment.ModEnchantmentRegistry;
import net.piinut.sp.entitiy.ModEntityRegistry;
import net.piinut.sp.item.ModItemRegistry;
import net.piinut.sp.recipe.ModRecipeRegistry;
import net.piinut.sp.world.biome.ModBiomeRegistry;
import net.piinut.sp.world.feature.ModConfiguredFeatureRegistry;
import net.piinut.sp.world.feature.ModFeatureRegistry;
import net.piinut.sp.world.feature.structure.ModStructureRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main implements ModInitializer {

    public static final String MODID = "sp";

    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final ItemGroup MOD_ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(Main.MODID, "general"), ()->new ItemStack(ModItemRegistry.SLIME_BALL));

    @Override
    public void onInitialize() {
        LOGGER.info("Slimy Project Loaded!");
        ModItemRegistry.registerAll();
        ModBlockRegistry.registerAll();
        ModBlockEntityRegistry.registerAll();
        ModEntityRegistry.registerAll();
        ModFeatureRegistry.registerAll();
        ModConfiguredFeatureRegistry.registerAll();
        ModStructureRegistry.registerAll();
        ModBiomeRegistry.registerAll();
        ModRecipeRegistry.registerAll();
        ModEnchantmentRegistry.registerAll();
    }
}
