package net.torocraft.torcharrows;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.ResourceLocation;

public class TorchArrowRenderer extends ArrowRenderer<EntityTorchArrow, ArrowRenderState> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(TorchArrows.MODID, "textures/entity/projectiles/torch_arrow.png");

    public TorchArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    public ResourceLocation getTextureLocation(ArrowRenderState renderState) {
        return TEXTURE;
    }
}
