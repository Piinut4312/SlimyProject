package net.piinut.sp.block.blockEntity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.piinut.sp.Main;
import net.piinut.sp.block.ModBlockRegistry;

public class ModBlockEntityRegistry {

    public static BlockEntityType<SlimeballCultivatorBlockEntity> SLIMEBALL_CULTIVATOR_BLOCK_ENTITY;

    public static void registerAll(){
        SLIMEBALL_CULTIVATOR_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(Main.MODID, "slimeball_cultivator")
                , FabricBlockEntityTypeBuilder.create(SlimeballCultivatorBlockEntity::new, ModBlockRegistry.SLIMEBALL_CULTIVATOR).build(null));
    }

}
