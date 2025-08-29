package net.torocraft.torcharrows;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemTorchArrow extends ArrowItem {

    public static final String NAME = "torch_arrow";

    public ItemTorchArrow(Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(Level level, ItemStack itemStack, LivingEntity shooter, ItemStack weapon) {
        return new EntityTorchArrow(level, shooter, itemStack);
    }

    // Override isInfinite to ensure torch arrows are always consumed
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        // Always return false to ensure torch arrows are consumed when fired
        return false;
    }

    // Helper method to get the registered item
    public static Item get() {
        return TorchArrows.TORCH_ARROW_ITEM.get();
    }
}