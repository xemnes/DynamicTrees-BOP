package dynamictreesbop.items;

import javax.annotation.Nullable;

import com.ferreusveritas.dynamictrees.items.Seed;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMapleSeed extends Seed {

	public ItemMapleSeed(String name) {
		super(name);
	}
	
	@Override
	public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }
	
	@Override
	@Nullable
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        return new EntityItemMapleSeed(world, location.posX, location.posY, location.posZ, itemstack);
    }
	
	public static class EntityItemMapleSeed extends EntityItem {
		
		public EntityItemMapleSeed(World worldIn) {
			super(worldIn);
		}
		
		public EntityItemMapleSeed(World worldIn, double x, double y, double z, ItemStack stack) {
			super(worldIn, x, y, z, stack);
			if (Math.abs(motionX) + Math.abs(motionZ) < 0.1) {
				this.setVelocity((worldIn.rand.nextDouble() - 0.5) / 5d, 0, (worldIn.rand.nextDouble() - 0.5) / 5d);
			}
			this.setDefaultPickupDelay();
		}
		
		@Override
		public void onUpdate() {
			
			//Counteract the air friction that will be applied in super.onUpdate() this results in a 0.99 factor instead of 0.98
			motionX *= 1.01f;
			motionZ *= 1.01f;
			
			//Add lift to counteract the gravity that will be applied in super.onUpdate()
			motionY += 0.03;
			
			super.onUpdate();
		}
		
		@Override
		public void setDefaultPickupDelay() {
	        this.setPickupDelay(50);
	    }
		
	}

}
