package net.trique.abysstest.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class AbyssBiomeTags {
    public static final TagKey<Biome> IS_ABYSS =
            TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("abysstest", "is_abyss"));

    public static final TagKey<Biome> IS_NIGHT_GROVE =
            TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("abysstest", "is_night_grove"));

    public static final TagKey<Biome> IS_AZURE_GROVE =
            TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("abysstest", "is_azure_grove"));

    public static final TagKey<Biome> IS_AMBER_GROVE =
            TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("abysstest", "is_amber_grove"));

    public static final TagKey<Biome> IS_CRYSTAL_CANYON =
            TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("abysstest", "is_crystal_canyon"));
}