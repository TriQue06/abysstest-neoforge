package net.trique.abysstest.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.trique.abysstest.AbyssTest;

import java.util.List;

public class AbyssPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SOMETHING_ORE_PLACED_KEY = registerKey("something_ore_placed");
    public static final ResourceKey<PlacedFeature> THING_ORE_PLACED_KEY = registerKey("thing_ore_placed");
    
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SOMETHING_ORE_PLACED_KEY, configuredFeatures.getOrThrow(AbyssConfiguredFeatures.SOMETHING_ORE_KEY),
                AbyssOrePlacement.commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(96))));
        register(context, THING_ORE_PLACED_KEY, configuredFeatures.getOrThrow(AbyssConfiguredFeatures.THING_ORE_KEY),
                AbyssOrePlacement.commonOrePlacement(50, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(96))));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AbyssTest.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}