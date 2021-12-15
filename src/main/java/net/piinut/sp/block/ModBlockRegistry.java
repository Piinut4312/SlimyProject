package net.piinut.sp.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.piinut.sp.Main;

public class ModBlockRegistry {

    public static final Block SLIME_LAYER = new SlimeLayerBlock(FabricBlockSettings.of(Material.DECORATION)
            .breakInstantly().collidable(false).breakByHand(true).slipperiness(0.8f)
            .sounds(BlockSoundGroup.SLIME).nonOpaque());

    public static final Block MAGMA_CREAM_LAYER = new MagmaCreamLayerBlock(FabricBlockSettings.of(Material.DECORATION)
            .breakInstantly().collidable(false).breakByHand(true).slipperiness(0.8f)
            .sounds(BlockSoundGroup.SLIME).nonOpaque().luminance(3));

    public static final Block GLOWING_SLIME_LAYER = new GlowingSlimeLayerBlock(FabricBlockSettings.of(Material.DECORATION)
            .breakInstantly().collidable(false).breakByHand(true).slipperiness(0.8f)
            .sounds(BlockSoundGroup.SLIME).nonOpaque().luminance(11));

    public static final Block GLOWING_SLIME_BLOCK = new GlowingSlimeBlock(FabricBlockSettings.of(Material.ORGANIC_PRODUCT)
            .breakInstantly().breakByHand(true).slipperiness(0.8f)
            .sounds(BlockSoundGroup.SLIME).nonOpaque().luminance(14));

    public static final Block HARDENED_SLIME_BLOCK = new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC)
            .strength(1.0f).requiresTool().sounds(BlockSoundGroup.CORAL));

    public static final Block HARDENED_SLIME_BRICK = new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC)
            .strength(1.0f).requiresTool().sounds(BlockSoundGroup.CORAL));

    public static final Block HARDENED_SLIME_BRICK_STAIRS = new ModStairsBlock(HARDENED_SLIME_BRICK.getDefaultState(), FabricBlockSettings.of(Material.SOLID_ORGANIC)
            .strength(1.0f).requiresTool().sounds(BlockSoundGroup.CORAL));

    public static final Block HARDENED_SLIME_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC)
            .strength(1.0f).requiresTool().sounds(BlockSoundGroup.CORAL));

    public static final Block CHISELED_HARDENED_SLIME_BRICK = new Block(FabricBlockSettings.of(Material.SOLID_ORGANIC)
            .strength(1.0f).requiresTool().sounds(BlockSoundGroup.CORAL));

    public static final Block SLIMY_DIRT_BLOCK = new SlimyDirtBlock(FabricBlockSettings.of(Material.SOIL)
            .strength(0.5f).sounds(BlockSoundGroup.GRAVEL).ticksRandomly().allowsSpawning((state, world, pos, type)-> true));

    public static final Block SLIMESHROOM_PLANT = new SlimeshroomPlantBlock(FabricBlockSettings.of(Material.PLANT)
            .noCollision().breakInstantly().sounds(BlockSoundGroup.SLIME).postProcess(ModBlockRegistry::always));

    public static final Block POTTED_SLIMESHROOM = new FlowerPotBlock(SLIMESHROOM_PLANT, FabricBlockSettings.of(Material.DECORATION)
            .breakInstantly().nonOpaque());

    public static final Block ENDER_SLIME_LAYER = new EnderSlimeLayerBlock(FabricBlockSettings.of(Material.DECORATION)
            .breakInstantly().breakByHand(true).slipperiness(0.8f).noCollision()
            .sounds(BlockSoundGroup.SLIME).nonOpaque().luminance(2));

    public static final Block LIVERWORT = new LiverwortBlock(FabricBlockSettings.of(Material.PLANT)
            .noCollision().breakInstantly().sounds(BlockSoundGroup.MOSS_CARPET).postProcess(ModBlockRegistry::always));

    public static final Block POTTED_LIVERWORT = new FlowerPotBlock(LIVERWORT, FabricBlockSettings.of(Material.DECORATION)
            .breakInstantly().nonOpaque());

    public static final Block SLIMESHROOM_BLOCK = new MushroomBlock(FabricBlockSettings.of(Material.WOOD)
            .strength(0.2f).sounds(BlockSoundGroup.WOOD));

    public static final Block MUSHROOM_STEM_BRICKS = new Block(FabricBlockSettings.of(Material.WOOD)
            .strength(0.4f).sounds(BlockSoundGroup.WOOD));

    public static final Block MUSHROOM_STEM_BRICK_STAIRS = new ModStairsBlock(MUSHROOM_STEM_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD)
            .strength(0.4f).sounds(BlockSoundGroup.WOOD));

    public static final Block MUSHROOM_STEM_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.WOOD)
            .strength(0.4f).sounds(BlockSoundGroup.WOOD));

    public static final Block COPPER_CHAIN = new OxidizableChainBlock(Oxidizable.OxidizationLevel.UNAFFECTED
            , FabricBlockSettings.of(Material.METAL, MapColor.CLEAR).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.CHAIN).nonOpaque());

    public static final Block EXPOSED_COPPER_CHAIN = new OxidizableChainBlock(Oxidizable.OxidizationLevel.EXPOSED
            , FabricBlockSettings.of(Material.METAL, MapColor.CLEAR).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.CHAIN).nonOpaque());

    public static final Block WEATHERED_COPPER_CHAIN = new OxidizableChainBlock(Oxidizable.OxidizationLevel.WEATHERED
            , FabricBlockSettings.of(Material.METAL, MapColor.CLEAR).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.CHAIN).nonOpaque());

    public static final Block OXIDIZED_COPPER_CHAIN = new OxidizableChainBlock(Oxidizable.OxidizationLevel.OXIDIZED
            , FabricBlockSettings.of(Material.METAL, MapColor.CLEAR).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.CHAIN).nonOpaque());

    public static final Block WAXED_COPPER_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL, MapColor.CLEAR)
            .requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.CHAIN).nonOpaque());

    public static final Block WAXED_EXPOSED_COPPER_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL, MapColor.CLEAR)
            .requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.CHAIN).nonOpaque());

    public static final Block WAXED_WEATHERED_COPPER_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL, MapColor.CLEAR)
            .requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.CHAIN).nonOpaque());

    public static final Block WAXED_OXIDIZED_COPPER_CHAIN = new ChainBlock(FabricBlockSettings.of(Material.METAL, MapColor.CLEAR)
            .requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.CHAIN).nonOpaque());

    public static final Block COPPER_LANTERN = new LanternBlock(FabricBlockSettings.of(Material.METAL)
            .requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance(15).nonOpaque());

    public static final Block COPPER_SOUL_LANTERN = new LanternBlock(FabricBlockSettings.of(Material.METAL)
            .requiresTool().strength(3.5F).sounds(BlockSoundGroup.LANTERN).luminance(10).nonOpaque());

    public static final Block SLIMEBALL_CULTIVATOR = new SlimeballCultivatorBlock(FabricBlockSettings.of(Material.METAL)
            .requiresTool().strength(8.0f).sounds(BlockSoundGroup.COPPER).nonOpaque());

    private static boolean always(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    private static void register(Block block, String id){
        Registry.register(Registry.BLOCK, new Identifier(Main.MODID, id), block);
    }

    private static void registerOxidizablePairs(Block less, Block more){
        OxidizableBlocksRegistry.registerOxidizableBlockPair(less, more);
    }

    private static void registerWaxablePairs(Block unwaxed, Block waxed){
        OxidizableBlocksRegistry.registerWaxableBlockPair(unwaxed, waxed);
    }

    public static void registerAll(){
        register(SLIME_LAYER, "slime_layer");
        register(MAGMA_CREAM_LAYER, "magma_cream_layer");
        register(GLOWING_SLIME_LAYER, "glowing_slime_layer");
        register(GLOWING_SLIME_BLOCK, "glowing_slime_block");
        register(HARDENED_SLIME_BLOCK, "hardened_slime_block");
        register(HARDENED_SLIME_BRICK, "hardened_slime_brick");
        register(HARDENED_SLIME_BRICK_STAIRS, "hardened_slime_brick_stairs");
        register(HARDENED_SLIME_BRICK_SLAB, "hardened_slime_brick_slab");
        register(CHISELED_HARDENED_SLIME_BRICK, "chiseled_hardened_slime_brick");
        register(SLIMY_DIRT_BLOCK, "slimy_dirt");
        register(SLIMESHROOM_PLANT, "slimeshroom");
        register(POTTED_SLIMESHROOM, "potted_slimeshroom");
        register(ENDER_SLIME_LAYER, "ender_slime_layer");
        register(LIVERWORT, "liverwort");
        register(POTTED_LIVERWORT, "potted_liverwort");
        register(SLIMESHROOM_BLOCK, "slimeshroom_block");
        register(MUSHROOM_STEM_BRICKS, "mushroom_stem_bricks");
        register(MUSHROOM_STEM_BRICK_STAIRS, "mushroom_stem_brick_stairs");
        register(MUSHROOM_STEM_BRICK_SLAB, "mushroom_stem_brick_slab");
        register(COPPER_CHAIN, "copper_chain");
        register(EXPOSED_COPPER_CHAIN, "exposed_copper_chain");
        register(WEATHERED_COPPER_CHAIN, "weathered_copper_chain");
        register(OXIDIZED_COPPER_CHAIN, "oxidized_copper_chain");
        register(WAXED_COPPER_CHAIN, "waxed_copper_chain");
        register(WAXED_EXPOSED_COPPER_CHAIN, "waxed_exposed_copper_chain");
        register(WAXED_WEATHERED_COPPER_CHAIN, "waxed_weathered_copper_chain");
        register(WAXED_OXIDIZED_COPPER_CHAIN, "waxed_oxidized_copper_chain");
        register(COPPER_LANTERN, "copper_lantern");
        register(COPPER_SOUL_LANTERN, "copper_soul_lantern");
        register(SLIMEBALL_CULTIVATOR, "slimeball_cultivator");
        registerOxidizablePairs(COPPER_CHAIN, EXPOSED_COPPER_CHAIN);
        registerOxidizablePairs(EXPOSED_COPPER_CHAIN, WEATHERED_COPPER_CHAIN);
        registerOxidizablePairs(WEATHERED_COPPER_CHAIN, OXIDIZED_COPPER_CHAIN);
        registerWaxablePairs(COPPER_CHAIN, WAXED_COPPER_CHAIN);
        registerWaxablePairs(EXPOSED_COPPER_CHAIN, WAXED_EXPOSED_COPPER_CHAIN);
        registerWaxablePairs(WEATHERED_COPPER_CHAIN, WAXED_WEATHERED_COPPER_CHAIN);
        registerWaxablePairs(OXIDIZED_COPPER_CHAIN, WAXED_OXIDIZED_COPPER_CHAIN);
    }

}
