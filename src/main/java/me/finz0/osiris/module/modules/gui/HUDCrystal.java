package me.finz0.osiris.module.modules.gui;

// Minecraft.
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Items;

// Java.
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.*;

// Finz0.
import me.finz0.osiris.module.ModuleManager;
import me.finz0.osiris.settings.Setting;
import me.finz0.osiris.module.Module;
import me.finz0.osiris.OsirisMod;

// Rina.
import rina.hud.OsirisPlusHUD;
import rina.util.TurokString;

/**
 * @author Rina!
 *
 * Created by Rina in 14/08/2020.
 *
 **/
public class HUDCrystal extends OsirisPlusHUD {
	int crystals = 0;

	public HUDCrystal() {
		super("Crystal", "Draw count crystal.");

		releaseHUDAsModule();
	}

	@Override
	public void onRenderHUD() {
		if (mc.player != null) {
			GlStateManager.pushMatrix();
			RenderHelper.enableGUIStandardItemLighting();

			crystals = mc.player.inventory.mainInventory.stream().filter(stack -> stack.getItem() == Items.END_CRYSTAL).mapToInt(ItemStack::getCount).sum();

			int off = 0;

			int calculed_width = 16;

			if (isInEditor()) {
				drawGUIRect(0, 0, this.w, this.h, 0, 0, 0, 100);
			}

			for (int i = 0; i < 45; i++) {
				ItemStack stack = mc.player.inventory.getStackInSlot(i);
				ItemStack off_h = mc.player.getHeldItemOffhand();

				if (off_h.getItem() == Items.END_CRYSTAL) {
					off = off_h.stackSize;
				} else {
					off = 0;
				}

				if (stack.getItem() == Items.END_CRYSTAL) {
					mc.getRenderItem().renderItemAndEffectIntoGUI(stack, this.x + verifyDocking(16, 0), this.y);
	
					calculed_width = 16 + 1 + getStringWidth("" + (int) (crystals + off));

					renderString("" + gray_color + (int) (crystals + off), 16 + 1, 6);

					break;
				}
			}

			mc.getRenderItem().zLevel = 0.0f;

			RenderHelper.disableStandardItemLighting();		
			
			GlStateManager.popMatrix();

			this.w = (calculed_width);
			this.h = 16;
		}
	}
}