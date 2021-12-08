package net.piinut.sp.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.piinut.sp.item.BlazingMagmaCreamItem;
import net.piinut.sp.item.ModItemRegistry;

public class BlazingMagmaCreamRecipe extends SpecialCraftingRecipe {

    public BlazingMagmaCreamRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {

        int magma_cream = 0;
        int blaze_powder = 0;

        for(int i = 0; i < inventory.size(); i++){
            ItemStack itemStack = inventory.getStack(i);
            if(!itemStack.isEmpty()){
                if(itemStack.getItem() instanceof BlazingMagmaCreamItem){
                    magma_cream++;
                }else if(itemStack.isOf(Items.BLAZE_POWDER)){
                    blaze_powder++;
                }else{
                    return false;
                }
            }
        }

        if(magma_cream > 1 || blaze_powder < 1){
            return false;
        }

        return true;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        ItemStack itemStack = new ItemStack(ModItemRegistry.BLAZING_MAGMA_CREAM);
        int blaze_powder = 0;
        boolean bl = true;
        int power = BlazingMagmaCreamItem.MIN_POWER;

        for(int i = 0; i < inventory.size(); i++){
            ItemStack itemStack2 = inventory.getStack(i);
            if(!itemStack2.isEmpty()){
                if(itemStack2.getItem() instanceof BlazingMagmaCreamItem){
                    power = BlazingMagmaCreamItem.getPower(itemStack2);
                    bl = false;
                }else if(itemStack2.isOf(Items.BLAZE_POWDER)){
                    blaze_powder++;
                }
            }
        }

        if(bl){
            return ItemStack.EMPTY;
        }

        BlazingMagmaCreamItem.setPower(itemStack, power + blaze_powder);

        return itemStack;
    }

    @Override
    public boolean fits(int width, int height) {
        return width*height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeRegistry.BLAZING_MAGMA_CREAM;
    }
}
