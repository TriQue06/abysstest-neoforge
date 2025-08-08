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

        nyliumBlockWithItem(AbyssBlocks.AZURE_NYLIUM, "abysstone");
        nyliumBlockWithItem(AbyssBlocks.NIGHT_NYLIUM, "abysstone");
        nyliumBlockWithItem(AbyssBlocks.AMBER_NYLIUM, "abysstone");
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void nyliumBlockWithItem(DeferredBlock<?> block, String baseBlockTextureName) {
        String name = block.getId().getPath();
        models().cubeBottomTop(
                name,
                modLoc("block/" + name + "_side"),      // yan yüzey (örn: azure_nylium_side.png)
                modLoc("block/" + baseBlockTextureName),// alt yüzey (örn: abysstone.png)
                modLoc("block/" + name)                 // üst yüzey (örn: azure_nylium.png)
        );
        simpleBlockWithItem(block.get(), models().getExistingFile(modLoc(name)));
    }
}