package net.piinut.sp.entitiy;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
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
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.piinut.sp.ClientMod;
import net.piinut.sp.block.ModBlockRegistry;
import net.piinut.sp.block.SlimeLayerBlock;
import net.piinut.sp.item.ModItemRegistry;
import net.piinut.sp.item.ModSlimeBallItem;

public class SlimeBallEntity extends ThrownItemEntity {

    private int stickiness;

    public SlimeBallEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
        this.stickiness = ModSlimeBallItem.MIN_STICKINESS;
    }

    public SlimeBallEntity(World world, LivingEntity owner, int stickiness) {
        super(ModEntityRegistry.SLIME_BALL_ENTITY_TYPE, owner, world);
        this.stickiness = stickiness;
    }

    public SlimeBallEntity(World world, double x, double y, double z) {
        super(ModEntityRegistry.SLIME_BALL_ENTITY_TYPE, x, y, z, world);
        this.stickiness = ModSlimeBallItem.MIN_STICKINESS;
    }

    @Override
    protected Item getDefaultItem() {
        return ModItemRegistry.SLIME_BALL;
    }

    @Environment(EnvType.CLIENT)
    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getItem();
        return itemStack.isEmpty() ? ParticleTypes.ITEM_SLIME : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
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

    private boolean canPutSlimeLayer(BlockState blockState){
        if(blockState.getBlock() instanceof SnowBlock){
            return blockState.get(SnowBlock.LAYERS) == 1;
        }

        return blockState.isAir() || blockState.getMaterial().isReplaceable();
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            BlockPos pos = new BlockPos(hitResult.getPos());
            BlockState blockState = ModBlockRegistry.SLIME_LAYER.getDefaultState().with(SlimeLayerBlock.STICKINESS, this.stickiness);

            if(canPutSlimeLayer(this.world.getBlockState(pos)) && blockState.canPlaceAt(world, pos)){
                this.world.setBlockState(pos, blockState);
                this.world.playSound(null, pos, SoundEvents.BLOCK_SLIME_BLOCK_PLACE, SoundCategory.NEUTRAL, 0.6F, 1.0F);
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
