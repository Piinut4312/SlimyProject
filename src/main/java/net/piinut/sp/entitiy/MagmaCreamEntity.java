package net.piinut.sp.entitiy;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.piinut.sp.ClientMod;
import net.piinut.sp.block.ModBlockRegistry;
import net.piinut.sp.item.ModItemRegistry;

public class MagmaCreamEntity extends ThrownItemEntity{
    public MagmaCreamEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public MagmaCreamEntity(World world, LivingEntity owner) {
        super(ModEntityRegistry.MAGMA_CREAM_ENTITY_TYPE, owner, world);
    }

    public MagmaCreamEntity(World world, double x, double y, double z) {
        super(ModEntityRegistry.MAGMA_CREAM_ENTITY_TYPE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItemRegistry.MAGMA_CREAM;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        return new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(ModItemRegistry.MAGMA_CREAM));
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
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
            BlockPos pos = new BlockPos(hitResult.getPos());
            BlockState state = world.getBlockState(pos);
            BlockState state2 = world.getBlockState(pos.down());
            if(state.isAir() && !state2.isAir()){
                this.world.setBlockState(pos, ModBlockRegistry.MAGMA_CREAM_LAYER.getDefaultState());
            }
            this.world.sendEntityStatus(this, (byte)3);
            this.kill();
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this, ClientMod.PacketID);
    }
}
