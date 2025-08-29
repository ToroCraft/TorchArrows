package net.torocraft.torcharrows;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = TorchArrows.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = TorchArrows.MODID, value = Dist.CLIENT)
public class TorchArrowsClient {
    public TorchArrowsClient(ModContainer container) {
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {        
        EntityRenderers.register(TorchArrows.ENTITY_TYPES_TORCH_ARROW.get(), TorchArrowRenderer::new);
    }
}
