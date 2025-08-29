package net.torocraft.torcharrows;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(TorchArrows.MODID)
public class TorchArrows {
    public static final String MODID = "torcharrows";
    public static final Logger LOGGER = LogUtils.getLogger();
    
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<Item, Item> TORCH_ARROW_ITEM = ITEMS.register("torch_arrow", () -> new ItemTorchArrow(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "torch_arrow")))));

    public static final DeferredHolder<EntityType<?>, EntityType<EntityTorchArrow>> ENTITY_TYPES_TORCH_ARROW = ENTITY_TYPES.register("torch_arrow", 
        () -> EntityType.Builder.<EntityTorchArrow>of(EntityTorchArrow::new, MobCategory.MISC)
            .sized(0.5f, 0.5f)
            .clientTrackingRange(4)
            .updateInterval(20)
            .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(MODID, "torch_arrow"))));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TORCH_ARROWS_TAB = CREATIVE_MODE_TABS.register("torch_arrows_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.torcharrows"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> TORCH_ARROW_ITEM.get().getDefaultInstance()) // Use torch arrow as icon
            .displayItems((parameters, output) -> {
                ITEMS.getEntries().forEach(item -> output.accept(item.get()));
            }).build());

    public TorchArrows(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        ITEMS.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            ITEMS.getEntries().forEach(item -> event.accept(item.get()));
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // @SubscribeEvent
    // public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
    //     if (!event.getEntity().level().isClientSide) {
    //         // Get the server and make the player OP
    //         var server = event.getEntity().getServer();
    //         if (server != null) {
    //             var gameProfile = event.getEntity().getGameProfile();
    //             server.getPlayerList().op(gameProfile);
    //             LOGGER.info("Automatically made {} a server operator", event.getEntity().getName().getString());
    //         }
    //     }
    // }
}
