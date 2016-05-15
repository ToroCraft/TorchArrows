package net.torocraft.torcharrowsmod.crafting;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.torocraft.torcharrowsmod.item.projectile.ItemTorchArrow;

public class TorchArrowsModRecipes {
	
	public static final void init() {
		torchArrow();
	}
	
	private static void torchArrow() {
		ItemStack stick = new ItemStack(Items.stick);
		ItemStack feather = new ItemStack(Items.feather);
		ItemStack coal = new ItemStack(Items.coal);
		ItemStack charcoal = new ItemStack(Items.coal, 1, 1);
		
		GameRegistry.addRecipe(new ItemStack(ItemTorchArrow.torchArrow), "c", "s", "f", 'c', coal, 's', stick, 'f', feather);
		GameRegistry.addRecipe(new ItemStack(ItemTorchArrow.torchArrow), "c", "s", "f", 'c', charcoal, 's', stick, 'f', feather);
	}	
}
