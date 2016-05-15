package net.torocraft.torcharrowsmod;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.torocraft.torcharrowsmod.crafting.TorchArrowsModRecipes;
import net.torocraft.torcharrowsmod.item.TorchArrowsModItems;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) {
        TorchArrowsModItems.init();
    	TorchArrowsModRecipes.init();
    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
