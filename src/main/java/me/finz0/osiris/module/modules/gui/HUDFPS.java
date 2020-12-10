package me.finz0.osiris.module.modules.gui;

// Minecraft.
import net.minecraft.util.math.MathHelper;

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
 * Created by Rina in 13/08/2020.
 *
 **/
public class HUDFPS extends OsirisPlusHUD {
	Setting rgb_effect;

	public HUDFPS() {
		super("FPS", "Draw how much frames per second.");

		rgb_effect = addSetting(new Setting("RGB", (Module) this, false, "HUDFPSHUDRGB"));

		releaseHUDAsModule();
	}

	@Override
	public void onRenderHUD() {
		String text = "FPS " + gray_color + mc.getDebugFPS();

		if (rgb_effect.getValBoolean()) {
			renderString(text, 0, 0, r_rgb, g_rgb, b_rgb);
		} else {
			renderString(text, 0, 0);
		}

		this.w = getStringWidth(text);
		this.h = getStringHeight(text);
	}
}