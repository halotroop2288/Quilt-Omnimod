package com.halotroop.omnimod.world.level.levelgen.feature;

import com.google.common.collect.ImmutableList;
import com.halotroop.omnimod.Omnimod;
import com.halotroop.omnimod.tags.OmnimodBlockTags;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectionContext;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.halotroop.omnimod.Omnimod.id;
import static com.halotroop.omnimod.world.level.block.OmnimodBlocks.*;
import static net.minecraft.world.level.levelgen.VerticalAnchor.*;
import static net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.TargetBlockState;
import static net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration.target;
import static net.minecraft.world.level.levelgen.placement.HeightRangePlacement.triangle;
import static net.minecraft.world.level.levelgen.placement.HeightRangePlacement.uniform;

/**
 * @author halotroop2288
 * @see OreFeatures
 */
public final class OmnimodOreFeatures implements ModInitializer {
	public static final RuleTest END_STONE;
	public static final RuleTest END_ORE_REPLACEABLES;

	/**
	 * End Ores
	 **/
	public static final List<TargetBlockState>
			ORE_END_COAL_TARGETS, ORE_END_COPPER_TARGETS, ORE_END_DIAMOND_TARGETS, ORE_END_EMERALD_TARGETS,
			ORE_END_GOLD_TARGETS, ORE_END_IRON_TARGETS, ORE_END_LAPIS_TARGETS, ORE_END_REDSTONE_TARGETS;

	public static final ConfiguredFeature<?, ?>[] END_ORE_CONFIGS;
	public static final PlacedFeature[] END_ORE_PLACED_FEATURES;

	static {
		END_STONE = new BlockMatchTest(Blocks.END_STONE);
		END_ORE_REPLACEABLES = new TagMatchTest(OmnimodBlockTags.BASE_STONE_END);

		//region Target List
		ORE_END_COAL_TARGETS = List.of(target(END_ORE_REPLACEABLES, END_COAL_ORE.defaultBlockState()));
		ORE_END_COPPER_TARGETS = List.of(target(END_ORE_REPLACEABLES, END_COPPER_ORE.defaultBlockState()));
		ORE_END_DIAMOND_TARGETS = List.of(target(END_ORE_REPLACEABLES, END_DIAMOND_ORE.defaultBlockState()));
		ORE_END_EMERALD_TARGETS = List.of(target(END_ORE_REPLACEABLES, END_EMERALD_ORE.defaultBlockState()));
		ORE_END_GOLD_TARGETS = List.of(target(END_ORE_REPLACEABLES, END_GOLD_ORE.defaultBlockState()));
		ORE_END_IRON_TARGETS = List.of(target(END_ORE_REPLACEABLES, END_IRON_ORE.defaultBlockState()));
		ORE_END_LAPIS_TARGETS = List.of(target(END_ORE_REPLACEABLES, END_LAPIS_ORE.defaultBlockState()));
		ORE_END_REDSTONE_TARGETS = List.of(target(END_ORE_REPLACEABLES, END_REDSTONE_ORE.defaultBlockState()));
		//endregion
		//region Configuration
		ConfiguredFeature<?, ?>
				CONFIGURED_END_COAL = configured(new OreConfiguration(END_ORE_REPLACEABLES, END_COAL_ORE.defaultBlockState(), 17)),
				CONFIGURED_END_COPPER = configured(new OreConfiguration(END_ORE_REPLACEABLES, END_COPPER_ORE.defaultBlockState(), 10)),
				CONFIGURED_END_DIAMOND = configured(new OreConfiguration(END_ORE_REPLACEABLES, END_DIAMOND_ORE.defaultBlockState(), 12)),
				CONFIGURED_END_EMERALD = configured(new OreConfiguration(END_ORE_REPLACEABLES, END_EMERALD_ORE.defaultBlockState(), 3)),
				CONFIGURED_END_GOLD = configured(new OreConfiguration(END_ORE_REPLACEABLES, END_GOLD_ORE.defaultBlockState(), 9)),
				CONFIGURED_END_IRON = configured(new OreConfiguration(END_ORE_REPLACEABLES, END_IRON_ORE.defaultBlockState(), 9)),
				CONFIGURED_END_LAPIS = configured(new OreConfiguration(END_ORE_REPLACEABLES, END_LAPIS_ORE.defaultBlockState(), 10)),
				CONFIGURED_END_REDSTONE = configured(new OreConfiguration(END_ORE_REPLACEABLES, END_REDSTONE_ORE.defaultBlockState(), 8));
		END_ORE_CONFIGS = new ConfiguredFeature<?, ?>[]{
				CONFIGURED_END_COAL, CONFIGURED_END_COPPER, CONFIGURED_END_DIAMOND, CONFIGURED_END_EMERALD,
				CONFIGURED_END_GOLD, CONFIGURED_END_IRON, CONFIGURED_END_LAPIS, CONFIGURED_END_REDSTONE
		};
		//endregion
		//region PlacedFeature
		PlacedFeature
				PLACED_END_COAL = new PlacedFeature(Holder.direct(CONFIGURED_END_COAL),
						commonOrePlacement(30, uniform(absolute(136), top()))),
				PLACED_END_COPPER = new PlacedFeature(Holder.direct(CONFIGURED_END_COPPER),
						commonOrePlacement(30, uniform(absolute(136), top()))),
				PLACED_END_DIAMOND = new PlacedFeature(Holder.direct(CONFIGURED_END_DIAMOND),
						commonOrePlacement(16, triangle(absolute(-16), absolute(112)))),
				PLACED_END_EMERALD = new PlacedFeature(Holder.direct(CONFIGURED_END_EMERALD),
						commonOrePlacement(100, triangle(absolute(0), VerticalAnchor.absolute(480)))),
				PLACED_END_GOLD = new PlacedFeature(Holder.direct(CONFIGURED_END_GOLD),
						commonOrePlacement(50, uniform(absolute(32), absolute(256)))),
				PLACED_END_IRON = new PlacedFeature(Holder.direct(CONFIGURED_END_IRON),
						commonOrePlacement(90, triangle(absolute(80), absolute(384)))),
				PLACED_END_LAPIS = new PlacedFeature(Holder.direct(CONFIGURED_END_LAPIS),
						commonOrePlacement(2, triangle(absolute(0), absolute(32)))),
				PLACED_END_REDSTONE = new PlacedFeature(Holder.direct(CONFIGURED_END_REDSTONE),
						commonOrePlacement(4, uniform(bottom(), absolute(15))));
		END_ORE_PLACED_FEATURES = new PlacedFeature[]{
				PLACED_END_COAL, PLACED_END_COPPER, PLACED_END_DIAMOND, PLACED_END_EMERALD,
				PLACED_END_GOLD, PLACED_END_IRON, PLACED_END_LAPIS, PLACED_END_REDSTONE
		};
		//endregion
	}

	@Override
	public void onInitialize(ModContainer mod) {
		if (Omnimod.config.spawn_end_ores) {
			var ids = registerConfigured("ore_end_%s", ORE_NAMES, END_ORE_CONFIGS);
			registerPlaced(ids, END_ORE_PLACED_FEATURES);
			addToBiome(BiomeSelectors.foundInTheEnd(), ids);
		}
	}

	private static ConfiguredFeature<?, ?> configured(@NotNull OreConfiguration config) {
		return new ConfiguredFeature<>(Feature.ORE, config);
	}

	/**
	 * Registers the ore configuration provided using the key provided.
	 *
	 * @param key   registry key for the configuration
	 * @param value the configuration to register
	 * @return the id used to register the configuration
	 */
	private static ResourceLocation registerConfigured(@NotNull String key, @NotNull ConfiguredFeature<?, ?> value) {
		final var id = id(key);
		BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, id, value);
		return id;
	}

	/**
	 * Registers each ore configuration provided using the formatted keys provided.
	 *
	 * @param format how to name the entries, use %s as a placeholder for a key
	 * @param keys   an array of strings to format as provided to make the registry key
	 * @param values an array of values to register
	 * @return the ids used to register the configurations
	 */
	private static List<ResourceLocation> registerConfigured(@NotNull String format,
	                                                         @NotNull String[] keys,
	                                                         @NotNull ConfiguredFeature<?, ?>[] values) {
		if (keys.length != values.length) {
			Omnimod.error("Supplied incorrect amount of keys for ConfiguredFeature registry of format: {}", format);
			return List.of();
		}
		List<ResourceLocation> ids = new ArrayList<>();
		for (int index = 0; index < keys.length; index++) {
			ids.add(registerConfigured(String.format(format, keys[index]), values[index]));
		}
		return ImmutableList.copyOf(ids);
	}

	/**
	 * Registers the placed feature provided using the id provided.
	 * @param id   registry id for the feature
	 * @param value the feature to register
	 */
	private static void registerPlaced(@NotNull ResourceLocation id, @NotNull PlacedFeature value) {
		Registry.register(BuiltinRegistries.PLACED_FEATURE, id, value);
	}

	/**
	 * Registers each ore configuration provided using the ids provided.
	 *
	 * @param ids   an array of strings to format as provided to make the registry key
	 * @param values an array of values to register
	 */
	private static void registerPlaced(@NotNull List<ResourceLocation> ids,
	                                   @NotNull PlacedFeature... values) {
		if (ids.size() != values.length) {
			Omnimod.error("Supplied incorrect amount of ids for a PlacedFeature");
			return;
		}
		for (int index = 0; index < ids.size(); index++) {
			registerPlaced(ids.get(index), values[index]);
		}
	}

	/**
	 * Registers the item provided using the id provided.
	 *
	 * @param biomeSelector the predicate that selects biomes to place the ore in
	 * @param id            registry id for the item
	 * @see BiomeSelectors
	 */
	private static void addToBiome(Predicate<BiomeSelectionContext> biomeSelector, ResourceLocation id) {
		BiomeModifications.addFeature(biomeSelector, GenerationStep.Decoration.UNDERGROUND_ORES,
				ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, id));
	}

	/**
	 * Adds each formatted key to biomes provided by the biome selector.
	 *
	 * @param biomeSelector the predicate that selects biomes to place the ores in
	 * @param ids           an array of strings to format as provided to make the registry key
	 * @see BiomeSelectors
	 */
	private static void addToBiome(Predicate<BiomeSelectionContext> biomeSelector, List<ResourceLocation> ids) {
		for (ResourceLocation id : ids) addToBiome(biomeSelector, id);
	}

	/**
	 * @see net.minecraft.data.worldgen.placement.OrePlacements
	 */
	private static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifier2) {
		return List.of(placementModifier, InSquarePlacement.spread(), placementModifier2, BiomeFilter.biome());
	}

	/**
	 * @see net.minecraft.data.worldgen.placement.OrePlacements
	 */
	private static List<PlacementModifier> commonOrePlacement(int count, PlacementModifier heightRange) {
		return orePlacement(CountPlacement.of(count), heightRange);
	}

	/**
	 * @see net.minecraft.data.worldgen.placement.OrePlacements
	 */
	private static List<PlacementModifier> rareOrePlacement(int chance, PlacementModifier heightRange) {
		return orePlacement(RarityFilter.onAverageOnceEvery(chance), heightRange);
	}
}
