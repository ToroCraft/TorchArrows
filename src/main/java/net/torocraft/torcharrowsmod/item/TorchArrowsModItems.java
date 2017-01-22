
package net.torocraft.torcharrowsmod.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.torocraft.torcharrowsmod.TorchArrowsMod;
import net.torocraft.torcharrowsmod.entity.projectile.EntityTorchArrow;
import net.torocraft.torcharrowsmod.entity.projectile.RenderTorchArrow;
import net.torocraft.torcharrowsmod.item.projectile.ItemTorchArrow;

public class TorchArrowsModItems {

	public static final void init() {
		initTools();
	}

	private static void initTools() {
		ItemTorchArrow.init();
	}
	
	@SideOnly(Side.CLIENT)
	public static final void registerRenders() {
		registerRendersTorchArrow();
		ItemTorchArrow.registerRenders();
	}

	@SideOnly(Side.CLIENT)
	private static void registerRendersTorchArrow() {
		EntityRegistry.registerModEntity(new ResourceLocation(TorchArrowsMod.MODID, "torcharrow"), EntityTorchArrow.class, "TorchArrow", 100, TorchArrowsMod.instance, 80, 10, true);
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();
		RenderingRegistry.registerEntityRenderingHandler(EntityTorchArrow.class, new RenderTorchArrow(rm));
	}

}
