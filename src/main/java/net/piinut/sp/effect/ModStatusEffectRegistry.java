package net.piinut.sp.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.piinut.sp.Main;

public class ModStatusEffectRegistry {

    public static final StatusEffect SOULLESS = new SoullessEffect();

    private static void register(String id, StatusEffect instance){
        Registry.register(Registry.STATUS_EFFECT, new Identifier(Main.MODID, id), instance);
    }

    public static void registerAll(){
        register("soulless", SOULLESS);
    }

}
