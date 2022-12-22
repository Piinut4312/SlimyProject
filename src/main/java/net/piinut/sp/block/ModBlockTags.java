package net.piinut.sp.block;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.piinut.sp.Main;

public class ModBlockTags {

    public static final Tag<Block> SOUL_BLOCK_CONVERTIBLE = TagFactory.BLOCK.create(new Identifier(Main.MODID, "soul_block_convertible"));

}
