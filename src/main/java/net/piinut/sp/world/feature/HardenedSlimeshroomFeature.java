package net.piinut.sp.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

import java.util.Random;

public class HardenedSlimeshroomFeature extends HugeMushroomFeature {
    public HardenedSlimeshroomFeature(Codec<HugeMushroomFeatureConfig> codec) {
        super(codec);
    }

    protected int getCapSize(int i, int j, int capSize, int y) {
        return capSize;
    }

    @Override
    protected int getHeight(Random random) {
        return 2+random.nextInt(2);
    }

    @Override
    protected void generateCap(WorldAccess world, Random random, BlockPos start, int y, BlockPos.Mutable mutable, HugeMushroomFeatureConfig config) {
        int i = config.foliageRadius;
        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                boolean bl = j == -i;
                boolean bl2 = j == i;
                boolean bl3 = k == -i;
                boolean bl4 = k == i;
                boolean bl5 = bl || bl2;
                boolean bl6 = bl3 || bl4;
                if (bl5 && bl6) continue;
                mutable.set(start, j, y, k);
                if (world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) continue;
                BlockState blockState = config.capProvider.getBlockState(random, start);
                this.setBlockState(world, mutable, blockState);
            }
        }
    }

}
