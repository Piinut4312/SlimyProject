package net.piinut.sp.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.piinut.sp.item.BlazingMagmaCreamItem;
import net.piinut.sp.item.ExplodingMagmaCreamItem;
import net.piinut.sp.item.ModItemRegistry;
import net.piinut.sp.item.ModSlimeBallItem;

public class SlimeBallRecipe extends SpecialCraftingRecipe {

    public SlimeBallRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int slime_ball = 0;
        int sugar = 0;

        for(int i = 0; i < inventory.size(); i++){
            ItemStack itemStack = inventory.getStack(i);
            if(!itemStack.isEmpty()){
                if(itemStack.getItem() instanceof ModSlimeBallItem){
                    slime_ball++;
                }else if(itemStack.isOf(Items.SUGAR)){
                    sugar++;
                }else{
                    return false;
                }
            }
        }

        if(slime_ball > 1 || sugar < 1){
            return false;
        }

        return true;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        ItemStack itemStack = new ItemStack(ModItemRegistry.SLIME_BALL);
        int sugar = 0;
        boolean bl = true;
        int stickiness = ModSlimeBallItem.MIN_STICKINESS;

        for(int i = 0; i < inventory.size(); i++){
            ItemStack itemStack2 = inventory.getStack(i);
            if(!itemStack2.isEmpty()){
                if(itemStack2.getItem() instanceof ModSlimeBallItem){
                    stickiness = ModSlimeBallItem.getStickiness(itemStack2);
                    bl = false;
                }else if(itemStack2.isOf(Items.SUGAR)){
                    sugar++;
                }
            }
        }

        if(bl){
            return ItemStack.EMPTY;
        }

        ModSlimeBallItem.setStickiness(itemStack, stickiness + sugar);

        return itemStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return width*height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeRegistry.SLIME_BALL;
    }
}
