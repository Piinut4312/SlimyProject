package net.piinut.sp.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

import java.util.Random;

public class HugeSlimeshroomFeature extends HugeMushroomFeature {

    public HugeSlimeshroomFeature(Codec<HugeMushroomFeatureConfig> codec) {
        super(codec);
    }

    private void setBlock(WorldAccess world, Random random, BlockPos pos, HugeMushroomFeatureConfig config, BlockPos.Mutable mutable){
        if (!world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) {
            this.setBlockState(world, mutable, config.stemProvider.getBlockState(random, pos));
        }
    }

    @Override
    protected int getHeight(Random random) {
        int i = random.nextInt(4);
        if (random.nextInt(12) == 0) {
            i *= 2;
        }
        i += 8;
        return i;
    }

    private int randomStemHeight(int height, Random random){
        return random.nextInt(Math.min(height-2, 3))+1;
    }

    @Override
    protected void generateStem(WorldAccess world, Random random, BlockPos pos, HugeMushroomFeatureConfig config, int height, BlockPos.Mutable mutable) {

        int n[] = new int[4];
        for(int i = 0; i < 4; i++){
            n[i] = randomStemHeight(height, random);
        }

        for(int i = 0; i < height; ++i) {
            if(height >= 2 && i <= 5){
                for(int j = -1; j <= 1; j++){
                    for(int k = -1; k <= 1; k++){
                        if(j*k != 0 && i < n[(j+1)/2+(k+1)]){
                            mutable.set(pos, j, i, k);
                            setBlock(world, random, pos, config, mutable);
                        }else if(i < 2){
                            mutable.set(pos, j, i, k);
                            setBlock(world, random, pos, config, mutable);
                        }
                    }
                }
            }
            if(i >= 2 && i <= 1+height/2){
                mutable.set(pos).move(-1, i, 0);
                setBlock(world, random, pos, config, mutable);
                mutable.set(pos).move(1, i, 0);
                if (!world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) {
                    this.setBlockState(world, mutable, config.stemProvider.getBlockState(random, pos));
                }
                mutable.set(pos).move(0, i, -1);
                setBlock(world, random, pos, config, mutable);
                mutable.set(pos).move(0, i, 1);
                setBlock(world, random, pos, config, mutable);
            }
            mutable.set(pos).move(Direction.UP, i);
            setBlock(world, random, pos, config, mutable);
        }
    }

    protected void generateCap(WorldAccess world, Random random, BlockPos start, int y, BlockPos.Mutable mutable, HugeMushroomFeatureConfig config) {
        //i = Y level of current cap generation?
        //y = Y level of top cap?
        for(int i = y - 4; i <= y; ++i) {
            //j = Radius of current level of cap generation?
            int j = config.foliageRadius;
            if(i == y){
                j -= 2;
            }else if(i == y-1 || i == y-4){
                j -= 1;
            }

            //l = x, m = z
            for(int l = -j; l <= j; ++l) {
                for(int m = -j; m <= j; ++m) {
                    boolean bl = l == -j;
                    boolean bl2 = l == j;
                    boolean bl3 = m == -j;
                    boolean bl4 = m == j;
                    boolean bl5 = bl || bl2;
                    boolean bl6 = bl3 || bl4;
                    boolean bl16 = l == -j+1;
                    boolean bl17 = l == j-1;
                    boolean bl18 = m == -j+1;
                    boolean bl19 = m == j-1;
                    boolean bl10 = bl || bl16;
                    boolean bl11 = bl2 || bl17;
                    boolean bl12 = bl3 || bl18;
                    boolean bl13 = bl4 || bl19;
                    boolean bl14 = bl10 || bl11;
                    boolean bl15 = bl12 || bl13;
                    boolean bl21 = (bl16 || bl17);
                    boolean bl22 = (bl18 || bl19);

                    boolean bl7 = i >= y;
                    boolean bl8 = (i == y-1 || i == y-4) && (bl5 != bl6);
                    boolean bl9 = i < y-1 && (bl14 != bl15) && (bl5 != bl6);
                    boolean bl20 = i < y-1 && (bl21 && bl22);

                    if (bl7 || bl8 || bl9 || bl20) {
                        mutable.set(start, l, i, m);
                        if (!world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) {
                            BlockState blockState = config.capProvider.getBlockState(random, start);
                            this.setBlockState(world, mutable, blockState);
                        }
                    }
                }
            }
        }

    }

    protected int getCapSize(int i, int j, int capSize, int y) {
        //y = height?
        //i = ????????
        //j = range??
        return y <= 4 ? 0 : capSize;
    }
}
