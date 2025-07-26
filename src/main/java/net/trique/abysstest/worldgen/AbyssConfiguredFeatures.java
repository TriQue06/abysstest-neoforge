package net.trique.abysstest.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.trique.abysstest.AbyssTest;
import net.trique.abysstest.block.AbyssBlocks;

import java.util.List;

public class AbyssConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOMETHING_ORE_KEY = registerKey("something_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THING_ORE_KEY = registerKey("thing_ore");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest abyssReplaceables = new BlockMatchTest(AbyssBlocks.ABYSSTONE.get());

        List<OreConfiguration.TargetBlockState> somethingOres = List.of(
                OreConfiguration.target(abyssReplaceables, AbyssBlocks.SOMETHING_ORE.get().defaultBlockState()));
        List<OreConfiguration.TargetBlockState> thingOres = List.of(
                OreConfiguration.target(abyssReplaceables, AbyssBlocks.THING_ORE.get().defaultBlockState()));

        register(context, SOMETHING_ORE_KEY, Feature.ORE, new OreConfiguration(abyssReplaceables,
                AbyssBlocks.SOMETHING_ORE.get().defaultBlockState(), 5));
        register(context, THING_ORE_KEY, Feature.ORE, new OreConfiguration(abyssReplaceables,
                AbyssBlocks.THING_ORE.get().defaultBlockState(), 5));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AbyssTest.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
