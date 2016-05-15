package net.torocraft.torcharrowsmod.item.projectile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemSpectralArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.torocraft.torcharrowsmod.TorchArrowsMod;
import net.torocraft.torcharrowsmod.entity.projectile.EntityTorchArrow;
import net.torocraft.torcharrowsmod.util.TorchArrowsUtils;

public class ItemTorchArrow extends ItemSpectralArrow {
	
	public static final String NAME = "torch_arrow";
	
	public static ItemTorchArrow torchArrow;
	
	public ItemTorchArrow(String unlocalizedName, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super();
		this.setUnlocalizedName(unlocalizedName);
	}
	
	public static void init() {
		torchArrow = new ItemTorchArrow(NAME, 1, EntityEquipmentSlot.OFFHAND);
		TorchArrowsUtils.registerItem(torchArrow, NAME);
	}

	public static void registerRenders() {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(torchArrow, 0, new ModelResourceLocation(TorchArrowsMod.MODID + ":" + NAME, "inventory"));
	}
	
	public EntityArrow makeTippedArrow(World worldIn, ItemStack itemStack, EntityLivingBase shooter) {
		return new EntityTorchArrow(worldIn, shooter);
	}
	
}
