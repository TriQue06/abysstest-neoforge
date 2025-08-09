package net.trique.abysstest.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.trique.abysstest.block.AbyssBlocks;
import net.trique.abysstest.item.AbyssItems;

import java.util.Set;

public class AbyssBlockLootTableProvider extends BlockLootSubProvider {
    protected AbyssBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(AbyssBlocks.ABYSSTONE.get());
        dropSelf(AbyssBlocks.SOMETHING_BLOCK.get());
        dropSelf(AbyssBlocks.THING_BLOCK.get());

        add(AbyssBlocks.AZURE_NYLIUM.get(),
                block -> createSilkTouchDispatchTable(
                        block,
                        applyExplosionDecay(block, LootItem.lootTableItem(AbyssBlocks.ABYSSTONE.get()))
                ));

        add(AbyssBlocks.NIGHT_NYLIUM.get(),
                block -> createSilkTouchDispatchTable(
                        block,
                        applyExplosionDecay(block, LootItem.lootTableItem(AbyssBlocks.ABYSSTONE.get()))
                ));

        add(AbyssBlocks.AMBER_NYLIUM.get(),
                block -> createSilkTouchDispatchTable(
                        block,
                        applyExplosionDecay(block, LootItem.lootTableItem(AbyssBlocks.ABYSSTONE.get()))
                ));

        add(AbyssBlocks.SOMETHING_ORE.get(),
                block -> createOreDrop(AbyssBlocks.SOMETHING_ORE.get(), AbyssItems.RAW_SOMETHING.get()));

        add(AbyssBlocks.THING_ORE.get(),
                block -> createOreDrop(AbyssBlocks.THING_ORE.get(), AbyssItems.RAW_THING.get()));

        if (AbyssBlocks.NIGHT_ROOTS != null) dropSelf(AbyssBlocks.NIGHT_ROOTS.get());
        if (AbyssBlocks.AZURE_ROOTS != null) dropSelf(AbyssBlocks.AZURE_ROOTS.get());
        if (AbyssBlocks.AMBER_ROOTS != null) dropSelf(AbyssBlocks.AMBER_ROOTS.get());
        if (AbyssBlocks.NIGHT_FUNGUS != null) dropSelf(AbyssBlocks.NIGHT_FUNGUS.get());
        if (AbyssBlocks.AZURE_FUNGUS != null) dropSelf(AbyssBlocks.AZURE_FUNGUS.get());
        if (AbyssBlocks.AMBER_FUNGUS != null) dropSelf(AbyssBlocks.AMBER_FUNGUS.get());
        if (AbyssBlocks.NIGHT_BUSH != null) dropSelf(AbyssBlocks.NIGHT_BUSH.get());
        if (AbyssBlocks.AZURE_BUSH != null) dropSelf(AbyssBlocks.AZURE_BUSH.get());
        if (AbyssBlocks.AMBER_BUSH != null) dropSelf(AbyssBlocks.AMBER_BUSH.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return AbyssBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}