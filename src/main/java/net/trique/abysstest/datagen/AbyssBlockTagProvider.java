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
                .add(AbyssBlocks.AZURE_NYLIUM.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(AbyssBlocks.ABYSSTONE.get())
                .add(AbyssBlocks.SOMETHING_ORE.get())
                .add(AbyssBlocks.SOMETHING_BLOCK.get())
                .add(AbyssBlocks.THING_ORE.get())
                .add(AbyssBlocks.THING_BLOCK.get())
                .add(AbyssBlocks.NIGHT_NYLIUM.get())
                .add(AbyssBlocks.AZURE_NYLIUM.get());
    }
}