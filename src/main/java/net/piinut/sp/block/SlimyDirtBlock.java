package net.piinut.sp.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.piinut.sp.Main;
import net.piinut.sp.enchantment.ModEnchantmentRegistry;

import java.util.Random;


public class SlimyDirtBlock extends SpreadableBlock {

    public SlimyDirtBlock(Settings settings) {
        super(settings);
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        boolean bl = true;
        if(entity instanceof LivingEntity){
            ItemStack itemStack = ((LivingEntity)entity).getEquippedStack(EquipmentSlot.FEET);
            if(EnchantmentHelper.getLevel(ModEnchantmentRegistry.BOUNCE, itemStack) > 0 && !entity.bypassesLandingEffects()){
                entity.handleFallDamage(fallDistance, 0.0F, DamageSource.FALL);
                bl = false;
            }
        }
        if(bl){
            entity.handleFallDamage(fallDistance, 0.6F, DamageSource.FALL);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        BlockState upBlock = world.getBlockState(pos.up());
        if(upBlock.isOf(Blocks.BROWN_MUSHROOM) && random.nextInt(5) == 0){
            BlockState newBlockState = ModBlockRegistry.SLIMESHROOM_PLANT.getDefaultState();
            world.setBlockState(pos.up(), newBlockState);
        }
        super.randomTick(state, world, pos, random);
    }

}
