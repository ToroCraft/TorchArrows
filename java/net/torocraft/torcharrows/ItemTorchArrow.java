package net.torocraft.torcharrows;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpectralArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class ItemTorchArrow extends ItemSpectralArrow {

	public static final String NAME = "torch_arrow";

	public static ItemTorchArrow INSTANCE;

	public ItemTorchArrow(String unlocalizedName, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		this.setUnlocalizedName(unlocalizedName);
	}

	@SubscribeEvent
	public static void init(final RegistryEvent.Register<Item> event) {
		INSTANCE = new ItemTorchArrow(NAME, 1, EntityEquipmentSlot.OFFHAND);
		INSTANCE.setRegistryName(TorchArrows.MODID, NAME);
		event.getRegistry().register(INSTANCE);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
				.register(INSTANCE, 0, new ModelResourceLocation(TorchArrows.MODID + ":" + NAME, "inventory"));
	}

	@Override
	public EntityArrow createArrow(World worldIn, ItemStack itemStack, EntityLivingBase shooter) {
		return new EntityTorchArrow(worldIn, shooter);
	}

}