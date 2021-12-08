package net.piinut.sp.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoods {

    public static final FoodComponent.Builder SLIMESHROOM_STEW = (new FoodComponent.Builder()).hunger(7).saturationModifier(0.6F).statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 400, 0), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0), 0.6f);
    public static final FoodComponent.Builder GOLDEN_SLIME_BALL = (new FoodComponent.Builder()).hunger(3).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 2400, 0), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0), 0.6f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1), 1.0F).alwaysEdible();
    public static final FoodComponent.Builder ENCHANTED_GOLDEN_SLIME_BALL = (new FoodComponent.Builder()).hunger(3).saturationModifier(1.2F).statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 2400, 2), 1.0f).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0), 0.6f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 1), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 2), 1.0f).alwaysEdible();
}
