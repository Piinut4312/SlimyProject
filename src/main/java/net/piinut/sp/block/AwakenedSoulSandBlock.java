package net.piinut.sp.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SoulSandBlock;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class AwakenedSoulSandBlock extends SoulSandBlock {

    public AwakenedSoulSandBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(6) == 0) {
            world.addParticle(ParticleTypes.SOUL, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + 0.85 + random.nextDouble(), (double)pos.getZ() + random.nextDouble(), 0.02D, 0.06D, 0.02D);
        }
    }
}
