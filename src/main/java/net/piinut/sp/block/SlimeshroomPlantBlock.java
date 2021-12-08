package net.piinut.sp.block;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.piinut.sp.world.feature.ModConfiguredFeatureRegistry;

import java.util.Random;
import java.util.function.Supplier;

public class SlimeshroomPlantBlock extends PlantBlock implements Fertilizable {

    private final Supplier<ConfiguredFeature<?, ?>> feature;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);

    public SlimeshroomPlantBlock(Settings settings) {
        super(settings);
        this.feature = () -> ModConfiguredFeatureRegistry.HUGE_SLIMESHROOM;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOpaqueFullCube(world, pos);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState blockState = world.getBlockState(blockPos);
        if (blockState.getBlock() == ModBlockRegistry.SLIMY_DIRT_BLOCK) {
            return true;
        } else {
            return world.getBaseLightLevel(pos, 0) < 13 && this.canPlantOnTop(blockState, world, blockPos);
        }
    }

    public boolean trySpawningBigMushroom(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        world.removeBlock(pos, false);
        if (this.feature.get().generate(world, world.getChunkManager().getChunkGenerator(), random, pos)) {
            return true;
        } else {
            world.setBlockState(pos, state, Block.NOTIFY_ALL);
            return false;
        }
    }

    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return true;
    }

    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)random.nextFloat() < 0.4D;
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.trySpawningBigMushroom(world, pos, state, random);
    }
}
