package net.piinut.sp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import net.piinut.sp.Main;

import java.util.Random;

public class SlimeLayerBlock extends LayerBlock {

    private static final float CONVERT_DIRT_CHANCE = 0.2f;

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
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.getBlockTickScheduler().schedule(pos, this, 100+world.getRandom().nextInt(50));
        if (world.isRaining() && world.hasRain(pos) && random.nextFloat() < RAIN_WASHING_CHANCE) {
            world.removeBlock(pos, false);
            BlockPos downPos = pos.down();
            Block downBlock = world.getBlockState(downPos).getBlock();
            if(downBlock == Blocks.DIRT || downBlock == Blocks.GRASS_BLOCK && canConvertDirt(world)){
                world.setBlockState(pos.down(), ModBlockRegistry.SLIMY_DIRT_BLOCK.getDefaultState());
            }
        }
    }

}
