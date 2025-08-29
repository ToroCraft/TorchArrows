package net.torocraft.torcharrows;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class EntityTorchArrow extends AbstractArrow {

    public EntityTorchArrow(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public EntityTorchArrow(Level level, LivingEntity shooter, ItemStack pickupItemStack) {
        super(TorchArrows.ENTITY_TYPES_TORCH_ARROW.get(), shooter, level, pickupItemStack, pickupItemStack);
    }

    public EntityTorchArrow(Level level, double x, double y, double z, ItemStack pickupItemStack) {
        super(TorchArrows.ENTITY_TYPES_TORCH_ARROW.get(), x, y, z, level, pickupItemStack, pickupItemStack);
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        
        if (!level().isClientSide) {
            placeTorchAtHit(result.getBlockPos(), result.getDirection());
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!level().isClientSide) {
            Entity entity = result.getEntity();
            // Set the entity on fire for 5 seconds (100 ticks)
            entity.setRemainingFireTicks(100);
            this.discard();
        }
    }

    private void placeTorchAtHit(BlockPos hitPos, Direction face) {
        Block torch = Blocks.TORCH;
        BlockState state = torch.defaultBlockState();
        BlockPos targetPos;
        
        if (face == Direction.UP) {
            // Place standing torch above the hit block
            targetPos = hitPos.relative(face);
            if (level().isEmptyBlock(targetPos)) {
                level().setBlock(targetPos, state, 3);
            } else {
                dropTorch(torch, targetPos);
            }
        } else if (face != Direction.DOWN) {
            // Place wall torch adjacent to the hit block
            targetPos = hitPos.relative(face);
            if (level().isEmptyBlock(targetPos)) {
                Block wallTorch = Blocks.WALL_TORCH;
                BlockState wallState = wallTorch.defaultBlockState();
                try {
                    wallState = wallState.setValue(net.minecraft.world.level.block.WallTorchBlock.FACING, face);
                    level().setBlock(targetPos, wallState, 3);
                } catch (Exception e) {
                    dropTorch(torch, targetPos);
                }
            } else {
                dropTorch(torch, targetPos);
            }
        } else {
            // Can't place on bottom, drop instead
            dropTorch(torch, hitPos);
        }
    }

    private void dropTorch(Block torch, BlockPos pos) {
        ItemStack torchStack = new ItemStack(torch);
        ItemEntity itemEntity = new ItemEntity(level(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, torchStack);
        level().addFreshEntity(itemEntity);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ItemTorchArrow.get());
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ItemTorchArrow.get());
    }

    public double getBaseDamage() {
        return 0.0; // Torch arrows don't do damage
    }
}
