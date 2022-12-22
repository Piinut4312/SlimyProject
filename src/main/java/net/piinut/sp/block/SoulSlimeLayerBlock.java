package net.piinut.sp.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.piinut.sp.effect.ModStatusEffectRegistry;

public class SoulSlimeLayerBlock extends AbstractSlimeLayerBlock {

    public SoulSlimeLayerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        boolean flag = false;
        if(entity instanceof PlayerEntity player){
            if(!(player.isCreative() || player.isSpectator())){
                player.addStatusEffect(new StatusEffectInstance(ModStatusEffectRegistry.SOULLESS, 200, 0, false, false));
                if(!player.world.isClient){
                    ((ServerWorld)entity.world).spawnParticles(ParticleTypes.SOUL, pos.getX()+0.5, pos.getY()+0.2f, pos.getZ()+0.5, 3, 0.02, 0.2, 0.02, 0.05);
                }
                flag = true;
            }
        }else if(entity instanceof MobEntity mobEntity){
            if(!mobEntity.isUndead()){
                mobEntity.addStatusEffect(new StatusEffectInstance(ModStatusEffectRegistry.SOULLESS, 600, 0, false, false));
                world.spawnEntity(new ExperienceOrbEntity(world, pos.getX(), pos.getY(), pos.getZ(), world.getRandom().nextInt(3)));
                if(!entity.world.isClient()){
                    ((ServerWorld)entity.world).spawnParticles(ParticleTypes.SOUL, pos.getX()+0.5, pos.getY()+0.2f, pos.getZ()+0.5, 3, 0.02, 0.2, 0.02, 0.05);
                }
                flag = true;
            }
        }
        if(flag){
            world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            entity.playSound(SoundEvents.PARTICLE_SOUL_ESCAPE, 1.0f, 1.0f);
            world.breakBlock(pos, false);
        }
        super.onEntityCollision(state, world, pos, entity);
    }

}
