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
		ItemStack stick = new ItemStack(Items.STICK);
		ItemStack feather = new ItemStack(Items.FEATHER);
		ItemStack coal = new ItemStack(Items.COAL);
		ItemStack charcoal = new ItemStack(Items.COAL, 1, 1);
		
		GameRegistry.addRecipe(new ItemStack(ItemTorchArrow.torchArrow, 4), "c", "s", "f", 'c', coal, 's', stick, 'f', feather);
		GameRegistry.addRecipe(new ItemStack(ItemTorchArrow.torchArrow, 4), "c", "s", "f", 'c', charcoal, 's', stick, 'f', feather);
	}	
}
