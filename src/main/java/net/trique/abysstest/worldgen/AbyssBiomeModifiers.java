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

    public static final ResourceKey<BiomeModifier> ADD_NIGHT_ROOTS = registerKey("add_night_roots");
    public static final ResourceKey<BiomeModifier> ADD_AZURE_ROOTS = registerKey("add_azure_roots");
    public static final ResourceKey<BiomeModifier> ADD_AMBER_ROOTS = registerKey("add_amber_roots");

    public static final ResourceKey<BiomeModifier> ADD_NIGHT_FUNGUS = registerKey("add_night_fungus");
    public static final ResourceKey<BiomeModifier> ADD_AZURE_FUNGUS = registerKey("add_azure_fungus");
    public static final ResourceKey<BiomeModifier> ADD_AMBER_FUNGUS = registerKey("add_amber_fungus");

    public static final ResourceKey<BiomeModifier> ADD_NIGHT_BUSH = registerKey("add_night_bush");
    public static final ResourceKey<BiomeModifier> ADD_AZURE_BUSH = registerKey("add_azure_bush");
    public static final ResourceKey<BiomeModifier> ADD_AMBER_BUSH = registerKey("add_amber_bush");

    public static final ResourceKey<BiomeModifier> ADD_STRANGE_CLUSTER = registerKey("add_strange_cluster");
    public static final ResourceKey<BiomeModifier> ADD_WEIRD_CLUSTER = registerKey("add_weird_cluster");
    public static final ResourceKey<BiomeModifier> ADD_ODD_CLUSTER = registerKey("add_odd_cluster");

    public static final ResourceKey<BiomeModifier> ADD_AZURE_MASS = registerKey("add_azure_mass");
    public static final ResourceKey<BiomeModifier> ADD_NIGHT_MASS = registerKey("add_night_mass");
    public static final ResourceKey<BiomeModifier> ADD_AMBER_MASS = registerKey("add_amber_mass");


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

        context.register(ADD_NIGHT_ROOTS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_NIGHT_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.NIGHT_ROOTS_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_AZURE_ROOTS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_AZURE_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.AZURE_ROOTS_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_AMBER_ROOTS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_AMBER_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.AMBER_ROOTS_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_NIGHT_FUNGUS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_NIGHT_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.NIGHT_FUNGUS_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_AZURE_FUNGUS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_AZURE_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.AZURE_FUNGUS_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_AMBER_FUNGUS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_AMBER_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.AMBER_FUNGUS_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_NIGHT_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_NIGHT_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.NIGHT_BUSH_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_AZURE_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_AZURE_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.AZURE_BUSH_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_AMBER_BUSH, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_AMBER_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.AMBER_BUSH_PLACED)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

        context.register(ADD_STRANGE_CLUSTER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_CRYSTAL_CANYON),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.STRANGE_CLUSTER_PLACED)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION));

        context.register(ADD_WEIRD_CLUSTER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_CRYSTAL_CANYON),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.WEIRD_CLUSTER_PLACED)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION));

        context.register(ADD_ODD_CLUSTER, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_CRYSTAL_CANYON),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.ODD_CLUSTER_PLACED)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION));
        
        context.register(ADD_AZURE_MASS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_AZURE_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.AZURE_MASS_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        
        context.register(ADD_NIGHT_MASS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_NIGHT_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.NIGHT_MASS_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        
        context.register(ADD_AMBER_MASS, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(AbyssBiomeTags.IS_AMBER_GROVE),
                HolderSet.direct(placedFeatures.getOrThrow(AbyssPlacedFeatures.AMBER_MASS_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(AbyssTest.MODID, name));
    }
}