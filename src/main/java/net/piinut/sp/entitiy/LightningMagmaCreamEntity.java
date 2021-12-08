package net.piinut.sp.entitiy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.piinut.sp.ClientMod;
import net.piinut.sp.item.ModItemRegistry;

public class LightningMagmaCreamEntity extends ThrownItemEntity {

    public LightningMagmaCreamEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public LightningMagmaCreamEntity(World world, LivingEntity owner) {
        super(ModEntityRegistry.LIGHTNING_MAGMA_CREAM_ENTITY_TYPE, owner, world);
    }

    public LightningMagmaCreamEntity(World world, double x, double y, double z) {
        super(ModEntityRegistry.LIGHTNING_MAGMA_CREAM_ENTITY_TYPE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItemRegistry.LIGHTNING_MAGMA_CREAM;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 0f);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            BlockPos blockPos = new BlockPos(hitResult.getPos());
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(this.world);
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(blockPos));
            this.world.spawnEntity(lightningEntity);
            this.world.sendEntityStatus(this, (byte)3);
            this.kill();
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this, ClientMod.PacketID);
    }
}
