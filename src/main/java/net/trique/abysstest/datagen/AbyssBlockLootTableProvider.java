package net.trique.abysstest.datagen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.trique.abysstest.block.AbyssBlocks;
import net.trique.abysstest.item.AbyssItems;

import java.util.Set;

public class AbyssBlockLootTableProvider extends BlockLootSubProvider {

    public AbyssBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(AbyssBlocks.ABYSSTONE.get());
        dropSelf(AbyssBlocks.SOMETHING_BLOCK.get());
        dropSelf(AbyssBlocks.THING_BLOCK.get());
        dropSelf(AbyssBlocks.AZURE_NYLIUM_MASS.get());
        dropSelf(AbyssBlocks.NIGHT_NYLIUM_MASS.get());
        dropSelf(AbyssBlocks.AMBER_NYLIUM_MASS.get());

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

        // Cevherler
        add(AbyssBlocks.SOMETHING_ORE.get(),
                b -> createOreDrop(AbyssBlocks.SOMETHING_ORE.get(), AbyssItems.RAW_SOMETHING.get()));
        add(AbyssBlocks.THING_ORE.get(),
                b -> createOreDrop(AbyssBlocks.THING_ORE.get(), AbyssItems.RAW_THING.get()));

        if (AbyssBlocks.NIGHT_ROOTS != null) dropSelf(AbyssBlocks.NIGHT_ROOTS.get());
        if (AbyssBlocks.AZURE_ROOTS != null) dropSelf(AbyssBlocks.AZURE_ROOTS.get());
        if (AbyssBlocks.AMBER_ROOTS != null) dropSelf(AbyssBlocks.AMBER_ROOTS.get());
        if (AbyssBlocks.NIGHT_FUNGUS != null) dropSelf(AbyssBlocks.NIGHT_FUNGUS.get());
        if (AbyssBlocks.AZURE_FUNGUS != null) dropSelf(AbyssBlocks.AZURE_FUNGUS.get());
        if (AbyssBlocks.AMBER_FUNGUS != null) dropSelf(AbyssBlocks.AMBER_FUNGUS.get());
        if (AbyssBlocks.NIGHT_BUSH != null) dropSelf(AbyssBlocks.NIGHT_BUSH.get());
        if (AbyssBlocks.AZURE_BUSH != null) dropSelf(AbyssBlocks.AZURE_BUSH.get());
        if (AbyssBlocks.AMBER_BUSH != null) dropSelf(AbyssBlocks.AMBER_BUSH.get());

        Holder<Enchantment> fortune = this.registries
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(Enchantments.FORTUNE);

        addClusterLoot(AbyssBlocks.STRANGE_CLUSTER.get(), AbyssItems.STRANGE_SHARD.get(), fortune);
        addClusterLoot(AbyssBlocks.WEIRD_CLUSTER.get(),   AbyssItems.WEIRD_SHARD.get(),   fortune);
        addClusterLoot(AbyssBlocks.ODD_CLUSTER.get(),     AbyssItems.ODD_SHARD.get(),     fortune);
    }

    private void addClusterLoot(Block cluster, ItemLike shard, Holder<Enchantment> fortune) {
        add(cluster, b -> createSilkTouchDispatchTable(
                b,
                applyExplosionDecay(b,
                        LootItem.lootTableItem(shard)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                .apply(ApplyBonusCount.addUniformBonusCount(fortune))
                )));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return AbyssBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}