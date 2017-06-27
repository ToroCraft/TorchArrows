package net.torocraft.torcharrows;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTorchArrow  extends RenderArrow<EntityTorchArrow> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(TorchArrows.MODID + ":textures/entity/projectiles/torch_arrow.png");

	public RenderTorchArrow(RenderManager rm) {
		super(rm);
	}

	protected ResourceLocation getEntityTexture(EntityTorchArrow entity) {
		return TEXTURE;
	}
}
