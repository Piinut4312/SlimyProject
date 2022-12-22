package net.piinut.sp.block;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import net.piinut.sp.Main;
import net.piinut.sp.block.sapling.ElmSaplingGenerator;
import org.jetbrains.annotations.Nullable;

public class ModBlockRegistry {

    public static final Material SLIME_LAYER_MATERIAL = new FabricMaterialBuilder(MapColor.LIME).allowsMovement().notSolid().lightPassesThrough().destroyedByPiston().replaceable().build();

    public static final Block SLIME_LAYER = new SlimeLayerBlock(FabricBlockSettings.of(SLIME_LAYER_MATERIAL)
            .breakInstantly().collidable(false).breakByHand(true).slipperiness(0.8f)
            .sounds(BlockSoundGroup.SLIME).nonOpaque());

    public static final Block MAGMA_CREAM_LAYER = new MagmaCreamLayerBlock(FabricBlockSettings.of(SLIME_LAYER_MATERIAL)
            .breakInstantly().collidable(false).breakByHand(true).slipperiness(0.8f)
            .sounds(BlockSoundGroup.SLIME).nonOpaque().luminance(3));

    public static final Block GLOWING_SLIME_LAYER = new GlowingSlimeLayerBlock(FabricBlockSettings.of(SLIME_LAYER_MATERIAL)
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

    public static final Block ENDER_SLIME_LAYER = new EnderSlimeLayerBlock(FabricBlockSettings.of(SLIME_LAYER_MATERIAL)
            .breakInstantly().breakByHand(true).slipperiness(0.8f).noCollision()
            .sounds(BlockSoundGroup.SLIME).nonOpaque().luminance(2));

    public static final Block SOUL_SLIME_LAYER = new SoulSlimeLayerBlock(FabricBlockSettings.of(SLIME_LAYER_MATERIAL)
            .breakInstantly().breakByHand(true).slipperiness(0.8f).noCollision()
            .sounds(BlockSoundGroup.SLIME).nonOpaque());

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

    public static final Block AWAKENED_SOUL_SAND_BLOCK = new AwakenedSoulSandBlock(FabricBlockSettings.of(Material.AGGREGATE, MapColor.BROWN).strength(0.5f).velocityMultiplier(0.4f).sounds(BlockSoundGroup.SOUL_SAND).allowsSpawning(ModBlockRegistry::always).solidBlock(ModBlockRegistry::always).blockVision(ModBlockRegistry::always).suffocates(ModBlockRegistry::always).luminance(10));

    public static final Block SLIME_MOLD = new SlimeMoldBlock(FabricBlockSettings.of(SLIME_LAYER_MATERIAL)
            .breakInstantly().breakByHand(true).slipperiness(0.8f).noCollision()
            .sounds(BlockSoundGroup.SLIME).nonOpaque());

    public static final Block SLIME_MOLD_MEMORY = new SlimeMoldMemoryBlock(FabricBlockSettings.of(Material.DECORATION).breakInstantly().sounds(BlockSoundGroup.WOOD));

    public static final Block ELM_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.OFF_WHITE : MapColor.STONE_GRAY).strength(2.0f).sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_ELM_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(2.0f).sounds(BlockSoundGroup.WOOD));

    public static final Block ELM_WOOD = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.STONE_GRAY).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD));

    public static final Block STRIPPED_ELM_WOOD = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD));

    public static final Block ELM_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD));

    public static final Block ELM_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.2f).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque().allowsSpawning(ModBlockRegistry::canSpawnOnLeaves).suffocates(ModBlockRegistry::never).blockVision(ModBlockRegistry::never));

    public static final Block ELM_SAPLING = new ElmSaplingBlock(new ElmSaplingGenerator(), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING));

    public static final Block ELM_SLAB = new SlabBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD));

    public static final Block ELM_STAIRS = new ModStairsBlock(ELM_PLANKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD));

    public static final Block ELM_BUTTON = new ModWoodenButtonBlock(FabricBlockSettings.of(Material.DECORATION).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD));

    public static final Block ELM_FENCE = new FenceBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD));

    public static final Block ELM_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD));

    public static final Block ELM_DOOR = new ModDoorBlock(FabricBlockSettings.of(Material.WOOD, MapColor.OFF_WHITE).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD));

    public static final Block ELM_PRESSURE_PLATE = new ModPressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, FabricBlockSettings.of(Material.WOOD).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD));

    private static boolean always(BlockState blockState, BlockView blockView, BlockPos blockPos, EntityType<?> entityType) {
        return true;
    }

    private static boolean always(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }

    private static void register(Block block, String id){
        Registry.register(Registry.BLOCK, new Identifier(Main.MODID, id), block);
    }

    private static void registerFlammables(Block block, int burn, int spread){
        FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread);
    }

    private static void registerOxidizablePairs(Block less, Block more){
        OxidizableBlocksRegistry.registerOxidizableBlockPair(less, more);
    }

    private static void registerWaxablePairs(Block unwaxed, Block waxed){
        OxidizableBlocksRegistry.registerWaxableBlockPair(unwaxed, waxed);
    }

    private static void registerStrippablePairs(Block input, Block stripped){
        StrippableBlockRegistry.register(input, stripped);
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
        register(SOUL_SLIME_LAYER, "soul_slime_layer");
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
        register(AWAKENED_SOUL_SAND_BLOCK, "awakened_soul_sand");
        register(SLIME_MOLD, "slime_mold");
        register(SLIME_MOLD_MEMORY, "slime_mold_memory");
        register(ELM_LOG, "elm_log");
        register(STRIPPED_ELM_LOG, "stripped_elm_log");
        register(ELM_WOOD, "elm_wood");
        register(STRIPPED_ELM_WOOD, "stripped_elm_wood");
        register(ELM_PLANKS, "elm_planks");
        register(ELM_LEAVES, "elm_leaves");
        register(ELM_SAPLING, "elm_sapling");
        register(ELM_SLAB, "elm_slab");
        register(ELM_STAIRS, "elm_stairs");
        register(ELM_BUTTON, "elm_button");
        register(ELM_FENCE, "elm_fence");
        register(ELM_FENCE_GATE, "elm_fence_gate");
        register(ELM_DOOR, "elm_door");
        register(ELM_PRESSURE_PLATE, "elm_pressure_plate");
        registerFlammables(ELM_PLANKS, 5, 20);
        registerFlammables(ELM_SLAB, 5, 20);
        registerFlammables(ELM_FENCE_GATE, 5, 20);
        registerFlammables(ELM_FENCE, 5, 20);
        registerFlammables(ELM_STAIRS, 5, 20);
        registerFlammables(ELM_LOG, 5, 5);
        registerFlammables(STRIPPED_ELM_LOG, 5, 5);
        registerFlammables(ELM_WOOD, 5, 5);
        registerFlammables(STRIPPED_ELM_WOOD, 5, 5);
        registerFlammables(ELM_LEAVES, 30, 60);
        registerOxidizablePairs(COPPER_CHAIN, EXPOSED_COPPER_CHAIN);
        registerOxidizablePairs(EXPOSED_COPPER_CHAIN, WEATHERED_COPPER_CHAIN);
        registerOxidizablePairs(WEATHERED_COPPER_CHAIN, OXIDIZED_COPPER_CHAIN);
        registerWaxablePairs(COPPER_CHAIN, WAXED_COPPER_CHAIN);
        registerWaxablePairs(EXPOSED_COPPER_CHAIN, WAXED_EXPOSED_COPPER_CHAIN);
        registerWaxablePairs(WEATHERED_COPPER_CHAIN, WAXED_WEATHERED_COPPER_CHAIN);
        registerWaxablePairs(OXIDIZED_COPPER_CHAIN, WAXED_OXIDIZED_COPPER_CHAIN);
        registerStrippablePairs(ELM_LOG, STRIPPED_ELM_LOG);
        registerStrippablePairs(ELM_WOOD, STRIPPED_ELM_WOOD);
    }

}
