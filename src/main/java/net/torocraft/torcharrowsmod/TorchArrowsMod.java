package net.torocraft.torcharrowsmod;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod (modid = TorchArrowsMod.MODID, name = TorchArrowsMod.MODNAME, version = TorchArrowsMod.VERSION)
public class TorchArrowsMod {
	
	public static final String MODID = "torcharrowsmod";
	public static final String VERSION = "1.9.4-2";
	public static final String MODNAME = "TorchArrowsMod";
	
	@SidedProxy(clientSide="net.torocraft.torcharrowsmod.ClientProxy", serverSide="net.torocraft.torcharrowsmod.ServerProxy")
	public static CommonProxy proxy;
	
	@Instance(value = TorchArrowsMod.MODID)
	public static TorchArrowsMod instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
	    proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
	    proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
	    proxy.postInit(e);
	}
	
}
