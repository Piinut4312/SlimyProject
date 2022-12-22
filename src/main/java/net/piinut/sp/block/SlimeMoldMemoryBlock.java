package net.piinut.sp.block;

import net.minecraft.block.AbstractRedstoneGateBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ComparatorBlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerTickScheduler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.TickPriority;
import net.minecraft.world.World;

import java.util.Random;

public class SlimeMoldMemoryBlock extends AbstractRedstoneGateBlock {

    public static final IntProperty POWER = Properties.POWER;

    protected SlimeMoldMemoryBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(POWER, 0).with(FACING, Direction.NORTH).with(POWERED, false));
    }

    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return 2;
    }

    @Override
    protected int getOutputLevel(BlockView world, BlockPos pos, BlockState state) {
        return state.get(POWER);
    }

    private boolean canWriteSignal(ServerWorld world, BlockPos pos, BlockState state){
        return this.getMaxInputLevelSides(world, pos, state) > 0;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int power = state.get(POWER);
        int i = this.getPower(world, pos, state);
        boolean b1 = this.canWriteSignal(world, pos, state);
        if(b1 && power != i){
            world.setBlockState(pos, state.with(POWER, i).with(POWERED, i > 0), Block.NOTIFY_LISTENERS);
        }else{
            world.getBlockTickScheduler().schedule(pos, this, this.getUpdateDelayInternal(state), TickPriority.VERY_HIGH);
        }
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if(state.get(FACING) == direction){
            return this.getOutputLevel(world, pos, state);
        }
        return 0;
    }

    @Override
    protected void updatePowered(World world, BlockPos pos, BlockState state) {
        boolean bl = state.get(POWERED);
        if (bl != this.hasPower(world, pos, state) && !world.getBlockTickScheduler().isTicking(pos, this)) {
            TickPriority tickPriority = TickPriority.HIGH;
            if (this.isTargetNotAligned(world, pos, state)) {
                tickPriority = TickPriority.EXTREMELY_HIGH;
            } else if (!bl) {
                tickPriority = TickPriority.VERY_HIGH;
            }
            world.getBlockTickScheduler().schedule(pos, this, this.getUpdateDelayInternal(state), tickPriority);
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, POWERED, POWER);
    }
}
