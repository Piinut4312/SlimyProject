package net.piinut.sp.entitiy.damage;

import net.minecraft.entity.damage.DamageSource;

public class ModDamageSource extends DamageSource {

    public ModDamageSource(String name) {
        super(name);
    }

    @Override
    public DamageSource setBypassesArmor() {
        return super.setBypassesArmor();
    }
}
