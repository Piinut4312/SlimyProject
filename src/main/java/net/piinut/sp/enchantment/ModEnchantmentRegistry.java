package net.piinut.sp.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.piinut.sp.Main;

public class ModEnchantmentRegistry {

    public static Enchantment BOUNCE;

    public static void registerAll(){
        BOUNCE = Registry.register(Registry.ENCHANTMENT
                , new Identifier(Main.MODID, "bounce")
                , new BounceEnchantment());
    }

}
