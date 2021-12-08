package net.piinut.sp.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SnowyBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.piinut.sp.block.ModBlockRegistry;

public class SlimeLayerPatchFeature extends Feature<DefaultFeatureConfig> {

    public SlimeLayerPatchFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockPos.Mutable mutable2 = new BlockPos.Mutable();
        int radius = context.getRandom().nextInt(3);

        for(int i = -radius; i <= radius; ++i) {
            for(int j = -radius; j <= radius; ++j) {
                int k = blockPos.getX() + i;
                int l = blockPos.getZ() + j;
                int m = structureWorldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, k, l);
                int chance = 1+(int)Math.sqrt(i*i+j*j);
                mutable.set(k, m, l);
                mutable2.set(mutable).move(Direction.DOWN, 1);
                if(structureWorldAccess.getBlockState(mutable2).isOf(ModBlockRegistry.SLIMY_DIRT_BLOCK) && context.getRandom().nextInt(chance) == 0){
                    structureWorldAccess.setBlockState(mutable, ModBlockRegistry.SLIME_LAYER.getDefaultState(), Block.NOTIFY_LISTENERS);
                }
            }
        }

        return true;
    }
}
