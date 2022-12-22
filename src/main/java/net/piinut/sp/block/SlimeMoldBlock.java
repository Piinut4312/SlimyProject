package net.piinut.sp.block;

import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.piinut.sp.item.ModItemTags;

import java.util.List;
import java.util.Random;

public class SlimeMoldBlock extends AbstractLichenBlock {

    public SlimeMoldBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.getBlockTickScheduler().schedule(pos, this, SlimeMoldBlock.getFoodConsumeDelay(world.getRandom()));
    }

    private static boolean canConsumeFood(Random random){
        return random.nextFloat() < 0.4;
    }

    private static int getFoodConsumeDelay(Random random){
        return 400 + random.nextInt(400);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.getBlockTickScheduler().schedule(pos, this, SlimeMoldBlock.getFoodConsumeDelay(world.getRandom()));
        if(SlimeMoldBlock.canConsumeFood(random)){
            List<ItemEntity> itemEntities = world.getEntitiesByClass(ItemEntity.class, new Box(pos.getX()-3, pos.getY()-1, pos.getZ()-3, pos.getX() + 4, pos.getY()+1, pos.getZ() + 4), itemEntity -> itemEntity != null && ModItemTags.SLIME_MOLD_FOOD.contains(itemEntity.getStack().getItem()));
            if(!itemEntities.isEmpty()){
                this.trySpreadRandomly(state, world, pos, random);
                for(ItemEntity itemEntity : itemEntities){
                    if(itemEntity.getBlockPos().isWithinDistance(pos, 1)){
                        itemEntity.getStack().decrement(1);
                        break;
                    }
                }
            }
        }
    }
}
