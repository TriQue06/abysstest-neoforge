package net.trique.abysstest.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.trique.abysstest.AbyssTest;
import net.trique.abysstest.tag.AbyssBiomeTags;

public class AbyssBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_SOMETHING_ORE = registerKey("add_something_ore");
    public static final ResourceKey<BiomeModifier> ADD_THING_ORE = registerKey("add_thing_ore");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_SOMETHING_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_ABYSS),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.SOMETHING_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


        context.register(ADD_THING_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_ABYSS),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.THING_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(AbyssTest.MODID, name));
    }
}