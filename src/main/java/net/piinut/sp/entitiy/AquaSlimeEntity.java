package net.piinut.sp.entitiy;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.loot.LootTables;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;

import java.util.Random;

public class AquaSlimeEntity extends SlimeEntity {

    public AquaSlimeEntity(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
    }

    @Override
    protected ParticleEffect getParticles() {
        return ParticleTypes.BUBBLE;
    }

    @Override
    public boolean canSpawn(WorldView world) {
        return world.intersectsEntities(this) && (world.containsFluid(this.getBoundingBox()) || this.world.isRaining());
    }

    public static boolean canAquaSlimeSpawn(EntityType<AquaSlimeEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getDifficulty() != Difficulty.PEACEFUL && (pos.getY() > 50 && pos.getY() < 70 && random.nextFloat() < 0.5f && random.nextFloat() < world.getMoonSize() && world.getLightLevel(pos) <= random.nextInt(11));
    }

    @Override
    protected Identifier getLootTableId() {
        return this.getSize() == 1 ? this.getType().getLootTableId() : LootTables.EMPTY;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void swimUpward(Tag<Fluid> fluid) {
        if (fluid == FluidTags.WATER) {
            Vec3d vec3d = this.getVelocity();
            this.setVelocity(vec3d.x, 0.22F + (float)this.getSize() * 0.05F, vec3d.z);
            this.velocityDirty = true;
        } else {
            super.swimUpward(fluid);
        }
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (!this.world.isClient) {
            int i;
            int j;
            int k;

            if(this.world.getDimension().isUltrawarm()){
                this.damage(DamageSource.ON_FIRE, 1.0F);
            }

            if(this.world.isRaining()){
                this.addStatusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 5));
            }

            if (!this.world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
                return;
            }

            BlockState blockState = Blocks.OBSIDIAN.getDefaultState();

            for(int l = 0; l < 4; ++l) {
                i = MathHelper.floor(this.getX() + (double)((float)(l % 2 * 2 - 1) * 0.25F));
                j = MathHelper.floor(this.getY()-1);
                k = MathHelper.floor(this.getZ() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
                BlockPos blockPos = new BlockPos(i, j, k);
                if (this.world.getBlockState(blockPos).isOf(Blocks.LAVA) && blockState.canPlaceAt(this.world, blockPos)) {
                    this.world.setBlockState(blockPos, blockState);
                }
            }
        }
    }
}
