package net.piinut.sp.entitiy;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.StainedGlassPaneBlock;
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
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.piinut.sp.ClientMod;
import net.piinut.sp.block.ModBlockRegistry;
import net.piinut.sp.item.ModItemRegistry;

public class HardenedSlimeBallEntity extends ThrownItemEntity{
    public HardenedSlimeBallEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public HardenedSlimeBallEntity(World world, LivingEntity owner) {
        super(ModEntityRegistry.HARDENED_SLIME_BALL_ENTITY_TYPE, owner, world);
    }

    public HardenedSlimeBallEntity(World world, double x, double y, double z) {
        super(ModEntityRegistry.HARDENED_SLIME_BALL_ENTITY_TYPE, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItemRegistry.HARDENED_SLIME_BALL;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        return new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(ModItemRegistry.HARDENED_SLIME_BALL));
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
        entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 2f);
        this.world.playSound(null, new BlockPos(entity.getPos()), SoundEvents.BLOCK_SLIME_BLOCK_HIT, SoundCategory.NEUTRAL, 1.0f, 0.1f);
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        BlockPos pos = blockHitResult.getBlockPos();
        if(this.world.getBlockState(pos).getBlock() instanceof PaneBlock){
            this.world.setBlockState(pos, Blocks.AIR.getDefaultState());
            this.world.playSound(null, pos, SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.world.sendEntityStatus(this, (byte)3);
            this.kill();
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntitySpawnPacket.create(this, ClientMod.PacketID);
    }
}
