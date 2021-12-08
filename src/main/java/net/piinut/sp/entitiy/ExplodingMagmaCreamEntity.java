package net.piinut.sp.entitiy;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.piinut.sp.ClientMod;
import net.piinut.sp.item.ModItemRegistry;

public class ExplodingMagmaCreamEntity extends ThrownItemEntity {

    private float power;
    private static final float max_power = 2.5f;
    private static final float default_power = 1.5f;

    public ExplodingMagmaCreamEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        this.power = default_power;
    }

    public ExplodingMagmaCreamEntity(World world, LivingEntity owner, float power) {
        super(ModEntityRegistry.EXPLODING_MAGMA_CREAM_ENTITY_TYPE, owner, world);
        this.power = Math.min(power, max_power);
    }

    public ExplodingMagmaCreamEntity(World world, double x, double y, double z) {
        super(ModEntityRegistry.EXPLODING_MAGMA_CREAM_ENTITY_TYPE, x, y, z, world);
        this.power = default_power;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItemRegistry.EXPLODING_MAGMA_CREAM;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 0f);
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        BlockPos pos = new BlockPos(hitResult.getPos());
        if (!this.world.isClient) {
            this.world.createExplosion(this, pos.getX(), pos.getY(), pos.getZ(), power, Explosion.DestructionType.DESTROY);
            this.world.sendEntityStatus(this, (byte)3);
            this.kill();
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this, ClientMod.PacketID);
    }
}
