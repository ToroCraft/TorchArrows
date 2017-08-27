package net.torocraft.torcharrows;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TorchArrows.MODID, name = TorchArrows.MODNAME, version = TorchArrows.VERSION)

public class TorchArrows {
	public static final String MODID = "torcharrowsmod";
	public static final String VERSION = "1.12.1-2";
	public static final String MODNAME = "TorchArrows";

	@Instance(value = TorchArrows.MODID)
	public static TorchArrows INSTANCE;

	@SidedProxy(clientSide="net.torocraft.torcharrows.ClientProxy", serverSide="net.torocraft.torcharrows.ServerProxy")
	public static CommonProxy proxy;

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
