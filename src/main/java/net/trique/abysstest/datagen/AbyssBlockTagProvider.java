package net.trique.abysstest.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.trique.abysstest.AbyssTest;
import net.trique.abysstest.block.AbyssBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class AbyssBlockTagProvider extends BlockTagsProvider {
    public AbyssBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AbyssTest.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(AbyssBlocks.ABYSSTONE.get())
                .add(AbyssBlocks.SOMETHING_ORE.get())
                .add(AbyssBlocks.SOMETHING_BLOCK.get())
                .add(AbyssBlocks.THING_ORE.get())
                .add(AbyssBlocks.THING_BLOCK.get())
                .add(AbyssBlocks.NIGHT_NYLIUM.get())
                .add(AbyssBlocks.AZURE_NYLIUM.get())
                .add(AbyssBlocks.AMBER_NYLIUM.get())
                .add(AbyssBlocks.STRANGE_CLUSTER.get())
                .add(AbyssBlocks.WEIRD_CLUSTER.get())
                .add(AbyssBlocks.ODD_CLUSTER.get())
                .add(AbyssBlocks.AZURE_NYLIUM_MASS.get())
                .add(AbyssBlocks.NIGHT_NYLIUM_MASS.get())
                .add(AbyssBlocks.AMBER_NYLIUM_MASS.get());

        tag(BlockTags.CRYSTAL_SOUND_BLOCKS)
                .add(AbyssBlocks.STRANGE_CLUSTER.get())
                .add(AbyssBlocks.WEIRD_CLUSTER.get())
                .add(AbyssBlocks.ODD_CLUSTER.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(AbyssBlocks.ABYSSTONE.get())
                .add(AbyssBlocks.SOMETHING_ORE.get())
                .add(AbyssBlocks.SOMETHING_BLOCK.get())
                .add(AbyssBlocks.THING_ORE.get())
                .add(AbyssBlocks.THING_BLOCK.get())
                .add(AbyssBlocks.NIGHT_NYLIUM.get())
                .add(AbyssBlocks.AZURE_NYLIUM.get())
                .add(AbyssBlocks.AMBER_NYLIUM.get())
                .add(AbyssBlocks.STRANGE_CLUSTER.get())
                .add(AbyssBlocks.WEIRD_CLUSTER.get())
                .add(AbyssBlocks.ODD_CLUSTER.get())
                .add(AbyssBlocks.AZURE_NYLIUM_MASS.get())
                .add(AbyssBlocks.NIGHT_NYLIUM_MASS.get())
                .add(AbyssBlocks.AMBER_NYLIUM_MASS.get());
    }
}