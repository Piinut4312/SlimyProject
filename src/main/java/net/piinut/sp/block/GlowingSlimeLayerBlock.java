package net.piinut.sp.block;

import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class GlowingSlimeLayerBlock extends AbstractSlimeLayerBlock {

    public GlowingSlimeLayerBlock(Settings settings) {
        super(settings);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

        super.randomDisplayTick(state, world, pos, random);
        if (random.nextInt(6) == 0) {
            world.addParticle(ParticleTypes.GLOW, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + random.nextDouble(), (double)pos.getZ() + random.nextDouble(), 0.0D, 0.02D, 0.0D);
        }

    }

}
