package net.piinut.sp.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import net.piinut.sp.Main;
import net.piinut.sp.block.ModBlockRegistry;
import net.piinut.sp.entitiy.ModEntityRegistry;

public class ModItemRegistry {

    public static final Item SLIME_BALL = new ModSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item MAGMA_CREAM = new ModMagmaCreamItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).fireproof());
    public static final Item GLOWING_SLIME_BALL = new GlowingSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item EXPLODING_MAGMA_CREAM = new ExplodingMagmaCreamItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).fireproof());
    public static final Item HARDENED_SLIME_BALL = new HardenedSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item BLAZING_MAGMA_CREAM = new BlazingMagmaCreamItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).fireproof());
    public static final Item ENDER_SLIME_BALL = new EnderSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item AQUA_SLIME_BALL = new AquaSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item SLIMESHROOM_STEW = new SlimeshroomStewItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).food(ModFoods.SLIMESHROOM_STEW.build()).maxCount(1));
    public static final Item LIGHTNING_MAGMA_CREAM = new LightningMagmaCreamItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).fireproof());
    public static final Item COPPER_NUGGET = new Item(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item GOLDEN_SLIME_BALL = new Item(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).food(ModFoods.GOLDEN_SLIME_BALL.build()).rarity(Rarity.RARE));
    public static final Item ENCHANTED_GOLDEN_SLIME_BALL = new EnchantedGoldenSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).food(ModFoods.ENCHANTED_GOLDEN_SLIME_BALL.build()).rarity(Rarity.EPIC));
    public static final BlockItem SLIMESHROOM = new BlockItem(ModBlockRegistry.SLIMESHROOM_PLANT, new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final BlockItem LIVERWORT = new BlockItem(ModBlockRegistry.LIVERWORT, new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final SpawnEggItem AQUA_SLIME_SPAWN_EGG = new SpawnEggItem(ModEntityRegistry.AQUA_SLIME_ENTITY_ENTITY_TYPE, 10868735, 7648767, new FabricItemSettings().group(Main.MOD_ITEM_GROUP));

    private static void register(Item item, String id){
        Registry.register(Registry.ITEM, new Identifier(Main.MODID, id), item);
    }

    private static void registerBlockItem(Block block, String id, ItemGroup group){
        register(new BlockItem(block, new FabricItemSettings().group(group)), id);
    }

    private static void registerBlockItem(Block block, String id){
        registerBlockItem(block, id, Main.MOD_ITEM_GROUP);
    }

    private static void registerCompostableItem(float levelIncreaseChance, ItemConvertible item) {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), levelIncreaseChance);
    }

    public static void registerAll(){
        register(HARDENED_SLIME_BALL, "hardened_slime_ball");
        register(GLOWING_SLIME_BALL, "glowing_slime_ball");
        register(EXPLODING_MAGMA_CREAM, "exploding_magma_cream");
        register(BLAZING_MAGMA_CREAM, "blazing_magma_cream");
        register(ENDER_SLIME_BALL, "ender_slime_ball");
        register(AQUA_SLIME_BALL, "aqua_slime_ball");
        register(LIGHTNING_MAGMA_CREAM, "lightning_magma_cream");
        register(GOLDEN_SLIME_BALL, "golden_slime_ball");
        register(ENCHANTED_GOLDEN_SLIME_BALL, "enchanted_golden_slime_ball");
        register(SLIMESHROOM, "slimeshroom");
        register(LIVERWORT, "liverwort");
        register(SLIMESHROOM_STEW, "slimeshroom_stew");
        register(COPPER_NUGGET, "copper_nugget");
        register(AQUA_SLIME_SPAWN_EGG, "aqua_slime_spawn_egg");
        registerBlockItem(ModBlockRegistry.SLIME_LAYER, "slime_layer");
        registerBlockItem(ModBlockRegistry.MAGMA_CREAM_LAYER, "magma_cream_layer");
        registerBlockItem(ModBlockRegistry.GLOWING_SLIME_LAYER, "glowing_slime_layer");
        registerBlockItem(ModBlockRegistry.ENDER_SLIME_LAYER, "ender_slime_layer");
        registerBlockItem(ModBlockRegistry.HARDENED_SLIME_BLOCK, "hardened_slime_block");
        registerBlockItem(ModBlockRegistry.HARDENED_SLIME_BRICK, "hardened_slime_brick");
        registerBlockItem(ModBlockRegistry.HARDENED_SLIME_BRICK_STAIRS, "hardened_slime_brick_stairs");
        registerBlockItem(ModBlockRegistry.HARDENED_SLIME_BRICK_SLAB, "hardened_slime_brick_slab");
        registerBlockItem(ModBlockRegistry.CHISELED_HARDENED_SLIME_BRICK, "chiseled_hardened_slime_brick");
        registerBlockItem(ModBlockRegistry.GLOWING_SLIME_BLOCK,"glowing_slime_block");
        registerBlockItem(ModBlockRegistry.SLIMY_DIRT_BLOCK, "slimy_dirt");
        registerBlockItem(ModBlockRegistry.SLIMESHROOM_BLOCK, "slimeshroom_block");
        registerBlockItem(ModBlockRegistry.COPPER_CHAIN, "copper_chain");
        registerBlockItem(ModBlockRegistry.EXPOSED_COPPER_CHAIN, "exposed_copper_chain");
        registerBlockItem(ModBlockRegistry.WEATHERED_COPPER_CHAIN, "weathered_copper_chain");
        registerBlockItem(ModBlockRegistry.OXIDIZED_COPPER_CHAIN, "oxidized_copper_chain");
        registerBlockItem(ModBlockRegistry.WAXED_COPPER_CHAIN, "waxed_copper_chain");
        registerBlockItem(ModBlockRegistry.WAXED_EXPOSED_COPPER_CHAIN, "waxed_exposed_copper_chain");
        registerBlockItem(ModBlockRegistry.WAXED_WEATHERED_COPPER_CHAIN, "waxed_weathered_copper_chain");
        registerBlockItem(ModBlockRegistry.WAXED_OXIDIZED_COPPER_CHAIN, "waxed_oxidized_copper_chain");
        registerBlockItem(ModBlockRegistry.COPPER_LANTERN, "copper_lantern");
        registerBlockItem(ModBlockRegistry.COPPER_SOUL_LANTERN, "copper_soul_lantern");
        registerBlockItem(ModBlockRegistry.SLIMEBALL_CULTIVATOR, "slimeball_cultivator");
        registerCompostableItem(0.65f, SLIMESHROOM);
        registerCompostableItem(0.65f, LIVERWORT);
    }

}
