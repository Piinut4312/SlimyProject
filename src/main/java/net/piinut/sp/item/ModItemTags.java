package net.piinut.sp.item;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.piinut.sp.Main;

public class ModItemTags {

    public static final Tag<Item> SLIMEBALL_CULTIVATOR_ACCEPTED = TagFactory.ITEM.create(new Identifier(Main.MODID, "slimeball_cultivator_accepted"));
    public static final Tag<Item> SLIMEBALL_CULTIVATOR_FOOD_SUPPLY = TagFactory.ITEM.create(new Identifier(Main.MODID, "slimeball_foods"));
    public static final Tag<Item> SLIMEBALL_CULTIVATOR_LIQUID_SUPPLY = TagFactory.ITEM.create(new Identifier(Main.MODID, "slimeball_cultivator_liquid_supply"));
    public static final Tag<Item> SLIME_MOLD_FOOD = TagFactory.ITEM.create(new Identifier(Main.MODID, "slime_mold_foods"));


}
