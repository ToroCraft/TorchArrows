package net.torocraft.torcharrows;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityTorchArrow extends EntitySpectralArrow {

	private static final String NAME = "TorchArrow";

	private double damage;
	private enum CollideAction {PLACE, BREAK, DROP}

	public static void init(int entityID) {
		ResourceLocation registryName = new ResourceLocation(TorchArrows.MODID, NAME);
		EntityRegistry
				.registerModEntity(registryName, EntityTorchArrow.class, registryName.toString(), entityID, TorchArrows.INSTANCE, 80, 10, true);
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		RenderManager rm = Minecraft.getMinecraft().getRenderManager();
		RenderingRegistry.registerEntityRenderingHandler(EntityTorchArrow.class, new RenderTorchArrow(rm));
	}

	public EntityTorchArrow(World worldIn) {
		super(worldIn);
		damage = 0.0;
	}

	public EntityTorchArrow(World worldIn, EntityLivingBase shooter) {
		super(worldIn, shooter);
		damage = 0.0;
	}

	public EntityTorchArrow(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		damage = 0.0;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote || !inGround) {
			return;
		}

		Block torch = Blocks.TORCH;
		IBlockState state = torch.getDefaultState();

		CollideAction action = CollideAction.PLACE;

		setDead();
		Vec3d vec3d1 = new Vec3d(posX, posY, posZ);
		Vec3d vec3d = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
		RayTraceResult rtr = world.rayTraceBlocks(vec3d1, vec3d, false, true, false);

		if (rtr == null || rtr.getBlockPos() == null) {
			dropTorch(torch, (int) posX, (int) posY, (int) posZ);
			return;
		}

		BlockPos pos = rtr.getBlockPos();

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (rtr.entityHit != null) {
			action = CollideAction.DROP;
		} else {
			if (world.getBlockState(pos).getBlock().getMaterial(state).equals(Material.VINE)) {
				action = CollideAction.BREAK;
			} else {
				switch (rtr.sideHit) {
				case DOWN:
					y--;
					break;
				case UP:
					y++;
					break;
				case NORTH:
					state = state.withProperty(BlockTorch.FACING, EnumFacing.NORTH);
					z--;
					break;
				case SOUTH:
					state = state.withProperty(BlockTorch.FACING, EnumFacing.SOUTH);
					z++;
					break;
				case WEST:
					state = state.withProperty(BlockTorch.FACING, EnumFacing.WEST);
					x--;
					break;
				case EAST:
					state = state.withProperty(BlockTorch.FACING, EnumFacing.EAST);
					x++;
					break;
				}

				pos = new BlockPos(x, y, z);
			}
		}

		switch (action) {
		case PLACE:
			if (world.isAirBlock(pos)) {
				world.setBlockState(pos, state);
			} else {
				dropTorch(torch, x, y, z);
			}
			break;
		case BREAK:
			world.destroyBlock(pos, true);
			world.setBlockState(pos, torch.getDefaultState());
			break;
		case DROP:
			dropTorch(torch, x, y, z);
			break;
		}
	}

	@Override
	protected void arrowHit(EntityLivingBase living) {
		dropTorch(Blocks.TORCH, living.chunkCoordX, living.chunkCoordY, living.chunkCoordZ);
	}

	@Override
	public double getDamage() {
		return 0.0;
	}

	private boolean dropTorch(Block torch, int x, int y, int z) {
		return world.spawnEntity(new EntityItem(world, x, y, z, new ItemStack(torch)));
	}

	@Override
	protected ItemStack getArrowStack() {
		return new ItemStack(Items.SPECTRAL_ARROW);
	}

}
