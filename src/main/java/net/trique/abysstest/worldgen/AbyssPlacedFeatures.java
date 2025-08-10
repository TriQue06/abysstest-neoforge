package net.trique.abysstest.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.trique.abysstest.AbyssTest;

import java.util.List;

public class AbyssPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SOMETHING_ORE_PLACED_KEY = registerKey("something_ore_placed");
    public static final ResourceKey<PlacedFeature> THING_ORE_PLACED_KEY     = registerKey("thing_ore_placed");

    public static final ResourceKey<PlacedFeature> NIGHT_ROOTS_PLACED = registerKey("night_roots_placed");
    public static final ResourceKey<PlacedFeature> AZURE_ROOTS_PLACED = registerKey("azure_roots_placed");
    public static final ResourceKey<PlacedFeature> AMBER_ROOTS_PLACED = registerKey("amber_roots_placed");

    public static final ResourceKey<PlacedFeature> NIGHT_FUNGUS_PLACED = registerKey("night_fungus_placed");
    public static final ResourceKey<PlacedFeature> AZURE_FUNGUS_PLACED = registerKey("azure_fungus_placed");
    public static final ResourceKey<PlacedFeature> AMBER_FUNGUS_PLACED = registerKey("amber_fungus_placed");

    public static final ResourceKey<PlacedFeature> NIGHT_BUSH_PLACED = registerKey("night_bush_placed");
    public static final ResourceKey<PlacedFeature> AZURE_BUSH_PLACED = registerKey("azure_bush_placed");
    public static final ResourceKey<PlacedFeature> AMBER_BUSH_PLACED = registerKey("amber_bush_placed");

    public static final ResourceKey<PlacedFeature> STRANGE_CLUSTER_PLACED = registerKey("strange_cluster_placed");
    public static final ResourceKey<PlacedFeature> WEIRD_CLUSTER_PLACED = registerKey("weird_cluster_placed");
    public static final ResourceKey<PlacedFeature> ODD_CLUSTER_PLACED = registerKey("odd_cluster_placed");

    public static final ResourceKey<PlacedFeature> AZURE_MASS_PLACED_KEY     = registerKey("azure_mass_placed");
    public static final ResourceKey<PlacedFeature> NIGHT_MASS_PLACED_KEY     = registerKey("night_mass_placed");
    public static final ResourceKey<PlacedFeature> AMBER_MASS_PLACED_KEY     = registerKey("amber_mass_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configured = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SOMETHING_ORE_PLACED_KEY,
                configured.getOrThrow(AbyssConfiguredFeatures.SOMETHING_ORE_KEY),
                AbyssOrePlacement.commonOrePlacement(
                        50, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(96))
                )
        );

        register(context, THING_ORE_PLACED_KEY,
                configured.getOrThrow(AbyssConfiguredFeatures.THING_ORE_KEY),
                AbyssOrePlacement.commonOrePlacement(
                        50, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(96))
                )
        );

        register(context, NIGHT_ROOTS_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.NIGHT_ROOTS_PATCH),
                rootsModifiers(1)
        );

        register(context, AZURE_ROOTS_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.AZURE_ROOTS_PATCH),
                rootsModifiers(1)
        );

        register(context, AMBER_ROOTS_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.AMBER_ROOTS_PATCH),
                rootsModifiers(1)
        );

        register(context, NIGHT_FUNGUS_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.NIGHT_FUNGUS_PATCH),
                rootsModifiers(1)
        );

        register(context, AZURE_FUNGUS_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.AZURE_FUNGUS_PATCH),
                rootsModifiers(1)
        );

        register(context, AMBER_FUNGUS_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.AMBER_FUNGUS_PATCH),
                rootsModifiers(1)
        );

        register(context, NIGHT_BUSH_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.NIGHT_BUSH_PATCH),
                rootsModifiers(1)
        );

        register(context, AZURE_BUSH_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.AZURE_BUSH_PATCH),
                rootsModifiers(1)
        );

        register(context, AMBER_BUSH_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.AMBER_BUSH_PATCH),
                rootsModifiers(1)
        );

        register(context, STRANGE_CLUSTER_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.STRANGE_CLUSTER_KEY),
                rootsModifiers(1)
        );

        register(context, WEIRD_CLUSTER_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.WEIRD_CLUSTER_KEY),
                rootsModifiers(1)
        );

        register(context, ODD_CLUSTER_PLACED,
                configured.getOrThrow(AbyssConfiguredFeatures.ODD_CLUSTER_KEY),
                rootsModifiers(1)
        );

        register(context, AZURE_MASS_PLACED_KEY,
                configured.getOrThrow(AbyssConfiguredFeatures.AZURE_MASS_KEY),
                AbyssOrePlacement.commonOrePlacement(
                        48, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(128))
                )
        );

        register(context, NIGHT_MASS_PLACED_KEY,
                configured.getOrThrow(AbyssConfiguredFeatures.NIGHT_MASS_KEY),
                AbyssOrePlacement.commonOrePlacement(
                        48, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(128))
                )
        );

        register(context, AMBER_MASS_PLACED_KEY,
                configured.getOrThrow(AbyssConfiguredFeatures.AMBER_MASS_KEY),
                AbyssOrePlacement.commonOrePlacement(
                        48, HeightRangePlacement.uniform(VerticalAnchor.absolute(32), VerticalAnchor.absolute(128))
                )
        );
    }

    private static List<PlacementModifier> rootsModifiers(int averageOnceEvery) {
        return List.of(
                RarityFilter.onAverageOnceEvery(averageOnceEvery),
                InSquarePlacement.spread(),
                PlacementUtils.FULL_RANGE,
                BiomeFilter.biome()
        );
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(AbyssTest.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context,
                                 ResourceKey<PlacedFeature> key,
                                 Holder<ConfiguredFeature<?, ?>> configuredFeature,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuredFeature, List.copyOf(modifiers)));
    }
}