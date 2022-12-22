package net.piinut.sp.item;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import net.piinut.sp.Main;
import net.piinut.sp.block.ModBlockRegistry;
import net.piinut.sp.entitiy.ModEntityRegistry;
import org.jetbrains.annotations.Nullable;

public class ModItemRegistry {

    public static final Item SLIME_BALL = new ModSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item MAGMA_CREAM = new ModMagmaCreamItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).fireproof());
    public static final Item GLOWING_SLIME_BALL = new GlowingSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item EXPLODING_MAGMA_CREAM = new ExplodingMagmaCreamItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).fireproof());
    public static final Item HARDENED_SLIME_BALL = new HardenedSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item BLAZING_MAGMA_CREAM = new BlazingMagmaCreamItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).fireproof());
    public static final Item ENDER_SLIME_BALL = new EnderSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item SOUL_SLIME_BALL = new SoulSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).rarity(Rarity.EPIC));
    public static final Item AQUA_SLIME_BALL = new AquaSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item SLIMESHROOM_STEW = new SlimeshroomStewItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).food(ModFoods.SLIMESHROOM_STEW.build()).maxCount(1));
    public static final Item LIGHTNING_MAGMA_CREAM = new LightningMagmaCreamItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).fireproof());
    public static final Item COPPER_NUGGET = new Item(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final Item GOLDEN_SLIME_BALL = new Item(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).food(ModFoods.GOLDEN_SLIME_BALL.build()).rarity(Rarity.RARE));
    public static final Item ENCHANTED_GOLDEN_SLIME_BALL = new EnchantedGoldenSlimeBallItem(new FabricItemSettings().group(Main.MOD_ITEM_GROUP).food(ModFoods.ENCHANTED_GOLDEN_SLIME_BALL.build()).rarity(Rarity.EPIC));
    public static final Item SLIME_MOLD_BALL = new Item(new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final BlockItem SLIMESHROOM = new BlockItem(ModBlockRegistry.SLIMESHROOM_PLANT, new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final BlockItem LIVERWORT = new BlockItem(ModBlockRegistry.LIVERWORT, new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final BlockItem ELM_LEAVES = new BlockItem(ModBlockRegistry.ELM_LEAVES, new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final TallBlockItem ELM_DOOR = new TallBlockItem(ModBlockRegistry.ELM_DOOR, new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final SpawnEggItem AQUA_SLIME_SPAWN_EGG = new SpawnEggItem(ModEntityRegistry.AQUA_SLIME_ENTITY_ENTITY_TYPE, 10868735, 7648767, new FabricItemSettings().group(Main.MOD_ITEM_GROUP));
    public static final SpawnEggItem SLIMOO_SPAWN_EGG = new SpawnEggItem(ModEntityRegistry.SLIMOO_ENTITY_ENTITY_TYPE, 8031057, 12111235, new FabricItemSettings().group(Main.MOD_ITEM_GROUP));

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
        register(SOUL_SLIME_BALL, "soul_slime_ball");
        register(AQUA_SLIME_BALL, "aqua_slime_ball");
        register(LIGHTNING_MAGMA_CREAM, "lightning_magma_cream");
        register(GOLDEN_SLIME_BALL, "golden_slime_ball");
        register(ENCHANTED_GOLDEN_SLIME_BALL, "enchanted_golden_slime_ball");
        register(SLIME_MOLD_BALL, "slime_mold_ball");
        register(SLIMESHROOM, "slimeshroom");
        register(LIVERWORT, "liverwort");
        register(SLIMESHROOM_STEW, "slimeshroom_stew");
        register(COPPER_NUGGET, "copper_nugget");
        register(AQUA_SLIME_SPAWN_EGG, "aqua_slime_spawn_egg");
        register(SLIMOO_SPAWN_EGG, "slimoo_spawn_egg");
        registerBlockItem(ModBlockRegistry.SLIME_LAYER, "slime_layer");
        registerBlockItem(ModBlockRegistry.MAGMA_CREAM_LAYER, "magma_cream_layer");
        registerBlockItem(ModBlockRegistry.GLOWING_SLIME_LAYER, "glowing_slime_layer");
        registerBlockItem(ModBlockRegistry.ENDER_SLIME_LAYER, "ender_slime_layer");
        registerBlockItem(ModBlockRegistry.SOUL_SLIME_LAYER, "soul_slime_layer");
        registerBlockItem(ModBlockRegistry.SLIME_MOLD, "slime_mold");
        registerBlockItem(ModBlockRegistry.ELM_LOG, "elm_log");
        registerBlockItem(ModBlockRegistry.STRIPPED_ELM_LOG, "stripped_elm_log");
        registerBlockItem(ModBlockRegistry.ELM_WOOD, "elm_wood");
        registerBlockItem(ModBlockRegistry.STRIPPED_ELM_WOOD, "stripped_elm_wood");
        registerBlockItem(ModBlockRegistry.ELM_PLANKS, "elm_planks");
        registerBlockItem(ModBlockRegistry.ELM_LEAVES, "elm_leaves");
        registerBlockItem(ModBlockRegistry.ELM_SAPLING, "elm_sapling");
        registerBlockItem(ModBlockRegistry.ELM_SLAB, "elm_slab");
        registerBlockItem(ModBlockRegistry.ELM_STAIRS, "elm_stairs");
        register(ELM_DOOR, "elm_door");
        registerBlockItem(ModBlockRegistry.ELM_PRESSURE_PLATE, "elm_pressure_plate");
        registerBlockItem(ModBlockRegistry.ELM_FENCE, "elm_fence");
        registerBlockItem(ModBlockRegistry.ELM_FENCE_GATE, "elm_fence_gate");
        registerBlockItem(ModBlockRegistry.ELM_BUTTON, "elm_button");
        registerBlockItem(ModBlockRegistry.HARDENED_SLIME_BLOCK, "hardened_slime_block");
        registerBlockItem(ModBlockRegistry.HARDENED_SLIME_BRICK, "hardened_slime_brick");
        registerBlockItem(ModBlockRegistry.HARDENED_SLIME_BRICK_STAIRS, "hardened_slime_brick_stairs");
        registerBlockItem(ModBlockRegistry.HARDENED_SLIME_BRICK_SLAB, "hardened_slime_brick_slab");
        registerBlockItem(ModBlockRegistry.CHISELED_HARDENED_SLIME_BRICK, "chiseled_hardened_slime_brick");
        registerBlockItem(ModBlockRegistry.GLOWING_SLIME_BLOCK,"glowing_slime_block");
        registerBlockItem(ModBlockRegistry.SLIMY_DIRT_BLOCK, "slimy_dirt");
        registerBlockItem(ModBlockRegistry.SLIMESHROOM_BLOCK, "slimeshroom_block");
        registerBlockItem(ModBlockRegistry.MUSHROOM_STEM_BRICKS, "mushroom_stem_bricks");
        registerBlockItem(ModBlockRegistry.MUSHROOM_STEM_BRICK_STAIRS, "mushroom_stem_brick_stairs");
        registerBlockItem(ModBlockRegistry.MUSHROOM_STEM_BRICK_SLAB, "mushroom_stem_brick_slab");
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
        registerBlockItem(ModBlockRegistry.SLIME_MOLD_MEMORY, "slime_mold_memory");
        register(new BlockItem(ModBlockRegistry.AWAKENED_SOUL_SAND_BLOCK, new FabricItemSettings().group(Main.MOD_ITEM_GROUP).rarity(Rarity.EPIC)), "awakened_soul_sand");
        registerCompostableItem(0.65f, SLIMESHROOM);
        registerCompostableItem(0.65f, LIVERWORT);
        ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> {
            BlockState blockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
            return blockState.getBlock() == ModBlockRegistry.ELM_LEAVES && tintIndex == 0? 0x6A9813:-1;
        }), ModItemRegistry.ELM_LEAVES);
    }

}
