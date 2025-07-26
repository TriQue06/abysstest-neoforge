package net.trique.abysstest.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.trique.abysstest.AbyssTest;
import net.trique.abysstest.block.AbyssBlocks;

public class AbyssBlockStateProvider extends BlockStateProvider {
    public AbyssBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AbyssTest.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(AbyssBlocks.ABYSSTONE);
        blockWithItem(AbyssBlocks.SOMETHING_ORE);
        blockWithItem(AbyssBlocks.SOMETHING_BLOCK);
        blockWithItem(AbyssBlocks.THING_ORE);
        blockWithItem(AbyssBlocks.THING_BLOCK);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
