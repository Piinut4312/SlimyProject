package net.piinut.sp.block;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EnderSlimeLayerBlock extends AbstractSlimeLayerBlock {

    public EnderSlimeLayerBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.velocityModified = true;
            livingEntity.setVelocity(0, 0, 0);
            livingEntity.velocityDirty = true;
            if (!world.isClient()) {
                world.getServer().execute(()->{
                    double d = livingEntity.getX();
                    double e = livingEntity.getY();
                    double f = livingEntity.getZ();

                    for (int i = 0; i < 16; ++i) {
                        double g = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5D) * 16.0D;
                        double h = MathHelper.clamp(livingEntity.getY() + (double) (livingEntity.getRandom().nextInt(16) - 8), world.getBottomY(), world.getBottomY() + world.getLogicalHeight() - 1);
                        double j = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5D) * 16.0D;

                        if(livingEntity instanceof ServerPlayerEntity){
                            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
                            boolean bl = false;
                            double k = h;
                            BlockPos blockPos = new BlockPos(g, h, j);
                            boolean bl2 = false;

                            while(!bl2 && blockPos.getY() > world.getBottomY()) {
                                BlockPos blockPos2 = blockPos.down();
                                BlockState blockState = world.getBlockState(blockPos2);
                                if (blockState.getMaterial().blocksMovement()) {
                                    bl2 = true;
                                } else {
                                    --k;
                                    blockPos = blockPos2;
                                }
                            }

                            if (bl2) {
                                serverPlayerEntity.requestTeleport(g, k, j);
                                if (world.isSpaceEmpty(serverPlayerEntity) && !world.containsFluid(serverPlayerEntity.getBoundingBox())) {
                                    bl = true;
                                }
                            }

                            if (!bl) {
                                serverPlayerEntity.requestTeleport(d, e, f);
                            } else {
                                world.sendEntityStatus(serverPlayerEntity, (byte)46);
                                SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                                world.playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                                entity.playSound(soundEvent, 1.0F, 1.0F);
                                break;
                            }
                        }else{
                            if (livingEntity.teleport(g, h, j, true)) {
                                SoundEvent soundEvent = entity instanceof FoxEntity ? SoundEvents.ENTITY_FOX_TELEPORT : SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                                world.playSound(null, d, e, f, soundEvent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                                entity.playSound(soundEvent, 1.0F, 1.0F);
                                break;
                            }
                        }
                    }
                });
            }
        }

    }

}
