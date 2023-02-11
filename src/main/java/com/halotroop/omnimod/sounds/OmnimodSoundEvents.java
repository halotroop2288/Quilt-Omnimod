package com.halotroop.omnimod.sounds;

import com.halotroop.omnimod.Omnimod;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

import static com.halotroop.omnimod.Omnimod.id;

/**
 * @see net.minecraft.sounds.SoundEvents
 */
public class OmnimodSoundEvents implements ModInitializer {
	/**
	 * Minecraft includes the following songs from Minecraft - Volume Alpha as ambient music:
	 * <ul>
	 *     <li>1: Key (nuance1)</li>
	 *     <li>3: Subwoofer Lullaby (hal1)</li>
	 *     <li>5: Living Mice (hal2)</li>
	 *     <li>7: Haggstrom (hal3)</li>
	 *     <li>8: Minecraft (calm1)</li>
	 *     <li>9: Oxygène (nuance2)</li>
	 *     <li>11: Mice on Venus (piano3)</li>
	 *     <li>12: Dry Hands (piano1)</li>
	 *     <li>13: Wet Hands (piano2)</li>
	 *     <li>14: Clark (calm2)</li>
	 *     <li>18: Sweden (calm3)</li>
	 *     <li>21: Danny (hal4)</li>
	 * </ul>
	 * And it includes the following songs from Minecraft - Volume Alpha as records:
	 * <ul>
	 *     <li>16: Thirteen</li>
	 *     <li>19: Cat</li>
	 * </ul>
	 * We add the rest of the records here, and the rest of the ambient songs though a built-in resource pack.
	 */
	public static final String[] MISSING_VOLUME_ALPHA_SONGS;

	static {
		MISSING_VOLUME_ALPHA_SONGS = new String[] {
				"door", // #2
				"death", // #4
				"moog_city", // #6
				"equinoxe", // #10
				"chris", // #15
				"excuse", // #17
				"dog", // #20
				"beginning", // #22
				"droopy_likes_ricochet", // #23
				"droopy_likes_your_face", // #24
		};
	}

	@Override
	public void onInitialize(ModContainer mod) {
		register("record_%s", MISSING_VOLUME_ALPHA_SONGS);
	}

	/**
	 * Registers the item provided using the key provided.
	 *
	 * @param key   registry key for the item
	 */
	private SoundEvent register(String key) {
		Omnimod.spewRegistration("soundevent", key);
		var id = id(key);
		return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
	}

	/**
	 * Registers each item provided using the formatted keys provided.
	 *
	 * @param format how to name the entries, use %s as a placeholder for a key
	 * @param keys   an array of strings to format as provided to make the registry key
	 */
	private void register(String format, String[] keys) {
		for (String key : keys) register(String.format(format, key));
	}
}
