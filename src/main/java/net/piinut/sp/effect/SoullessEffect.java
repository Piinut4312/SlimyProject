package net.piinut.sp.effect;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.piinut.sp.Main;
import net.piinut.sp.block.ModBlockTags;
import net.piinut.sp.entitiy.damage.ModDamageSources;

import java.util.Random;

public class SoullessEffect extends StatusEffect {

    protected SoullessEffect() {
        super(StatusEffectCategory.HARMFUL, 0x3b2d06);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
        if(!(entity instanceof PlayerEntity)){
            entity.noClip = false;
        }
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        Random random = new Random();
        if(entity instanceof PlayerEntity player){
            BlockPos pos = player.getBlockPos();
            if(!(player.isSpectator() || player.isCreative())){
                entity.damage(ModDamageSources.EXHAUSTED, 1.5f);
                if(!entity.world.isClient){
                    if(random.nextFloat() < 0.1f){
                        ((ServerWorld)entity.world).spawnParticles(ParticleTypes.SOUL, pos.getX()+0.5, pos.getY()+0.5f, pos.getZ()+0.5, 5, 0.04, 0.2, 0.04, 0.05);
                        entity.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        entity.playSound(SoundEvents.PARTICLE_SOUL_ESCAPE, 1.0f, 1.0f);
                    }
                }
            }
        }else{
            entity.damage(ModDamageSources.EXHAUSTED, 1.5f);
            entity.setVelocity(entity.getVelocity().multiply(0, 0, 0));
            entity.setVelocity(entity.getVelocity().subtract(0, 0.03, 0));
            entity.noClip = true;
            entity.setJumping(false);
            BlockPos pos = entity.getBlockPos();
            if(entity.getBlockStateAtPos().isIn(ModBlockTags.SOUL_BLOCK_CONVERTIBLE)){
                if(random.nextFloat() < 0.7){
                    entity.world.setBlockState(entity.getBlockPos(), Blocks.SOUL_SAND.getDefaultState());
                }else{
                    entity.world.setBlockState(entity.getBlockPos(), Blocks.SOUL_SOIL.getDefaultState());
                }
            }
            if(!entity.world.isClient){
                if(random.nextFloat() < 0.1f){
                    ((ServerWorld)entity.world).spawnParticles(ParticleTypes.SOUL, pos.getX()+0.5, pos.getY()+0.5f, pos.getZ()+0.5, 5, 0.04, 0.2, 0.04, 0.05);
                    entity.world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.PARTICLE_SOUL_ESCAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    entity.playSound(SoundEvents.PARTICLE_SOUL_ESCAPE, 1.0f, 1.0f);
                    if(random.nextFloat() < 0.4f){
                        entity.world.spawnEntity(new ExperienceOrbEntity(entity.world, pos.getX(), pos.getY(), pos.getZ(), 1));
                    }
                }
            }
        }
    }
}
