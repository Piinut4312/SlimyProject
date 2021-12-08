package net.piinut.sp.entitiy;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.piinut.sp.ClientMod;
import net.piinut.sp.item.ModItemRegistry;

public class BlazingMagmaCreamEntity extends ThrownItemEntity {

    private int power;

    public BlazingMagmaCreamEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        this.power = 1;
    }

    public BlazingMagmaCreamEntity(World world, LivingEntity owner, int power) {
        super(ModEntityRegistry.BLAZING_MAGMA_CREAM_ENTITY_TYPE, owner, world);
        this.power = power;
    }

    public BlazingMagmaCreamEntity(World world, double x, double y, double z) {
        super(ModEntityRegistry.BLAZING_MAGMA_CREAM_ENTITY_TYPE, x, y, z, world);
        this.power = 1;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItemRegistry.BLAZING_MAGMA_CREAM;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 1f);
        entity.setOnFireFor(5+this.power);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.spawnFire(4*this.power);
            this.world.playSound(null, new BlockPos(hitResult.getPos()), SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 0.6f, 1.0f);
            this.world.sendEntityStatus(this, (byte)3);
            this.kill();
        }
    }

    private void spawnFire(int spreadAttempts) {
        if (!this.world.isClient && this.world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
            BlockPos blockPos = this.getBlockPos();
            BlockState blockState = AbstractFireBlock.getState(this.world, blockPos);
            if (this.world.getBlockState(blockPos).isAir() && blockState.canPlaceAt(this.world, blockPos)) {
                this.world.setBlockState(blockPos, blockState);
            }

            for(int i = 0; i < spreadAttempts; ++i) {
                BlockPos blockPos2 = blockPos.add(this.random.nextInt(2*this.power+1) - this.power, this.random.nextInt(3) - 1, this.random.nextInt(2*this.power+1) - this.power);
                blockState = AbstractFireBlock.getState(this.world, blockPos2);
                if (this.world.getBlockState(blockPos2).isAir() && blockState.canPlaceAt(this.world, blockPos2)) {
                    this.world.setBlockState(blockPos2, blockState);
                }
            }

        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this, ClientMod.PacketID);
    }
}
