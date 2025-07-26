package net.trique.abysstest.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class AbyssBiomeTags {
    public static final TagKey<Biome> IS_ABYSS =
            TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("abysstest", "is_abyss"));
}
