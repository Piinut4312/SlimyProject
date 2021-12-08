package net.piinut.sp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;

public class SlimeLayerBlock extends LayerBlock {

    private static final float CONVERT_DIRT_CHANCE = 0.4f;

    public SlimeLayerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
    }

    private static boolean canConvertDirt(World world){
        return world.getRandom().nextFloat() < CONVERT_DIRT_CHANCE;
    }

    @Override
    public void precipitationTick(BlockState state, World world, BlockPos pos, Biome.Precipitation precipitation) {
        if(canRainWash(world, precipitation)){
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            BlockPos downPos = pos.down();
            Block downBlock = world.getBlockState(downPos).getBlock();
            if(downBlock == Blocks.DIRT || downBlock == Blocks.GRASS_BLOCK && canConvertDirt(world)){
                world.setBlockState(pos.down(), ModBlockRegistry.SLIMY_DIRT_BLOCK.getDefaultState());
            }
        }
    }
}
