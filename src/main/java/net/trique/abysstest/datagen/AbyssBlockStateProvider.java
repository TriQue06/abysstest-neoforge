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

        // Nylium blokları (top/side/bottom ayrımı)
        nyliumBlockWithItem(AbyssBlocks.AZURE_NYLIUM, "abysstone");
        nyliumBlockWithItem(AbyssBlocks.NIGHT_NYLIUM, "abysstone");
        nyliumBlockWithItem(AbyssBlocks.AMBER_NYLIUM, "abysstone");

        // Roots tipleri: dünyada cross, item’de 2D sprite
        crossBlockWithItem2D(AbyssBlocks.NIGHT_ROOTS);
        crossBlockWithItem2D(AbyssBlocks.AZURE_ROOTS);
        crossBlockWithItem2D(AbyssBlocks.AMBER_ROOTS);
        crossBlockWithItem2D(AbyssBlocks.NIGHT_FUNGUS);
        crossBlockWithItem2D(AbyssBlocks.AZURE_FUNGUS);
        crossBlockWithItem2D(AbyssBlocks.AMBER_FUNGUS);
        crossBlockWithItem2D(AbyssBlocks.NIGHT_BUSH);
        crossBlockWithItem2D(AbyssBlocks.AZURE_BUSH);
        crossBlockWithItem2D(AbyssBlocks.AMBER_BUSH);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void nyliumBlockWithItem(DeferredBlock<?> block, String baseBlockTextureName) {
        String name = block.getId().getPath();
        models().cubeBottomTop(
                name,
                modLoc("block/" + name + "_side"),       // side
                modLoc("block/" + baseBlockTextureName), // bottom
                modLoc("block/" + name)                  // top
        );
        simpleBlockWithItem(block.get(), models().getExistingFile(modLoc(name)));
    }

    private void crossBlockWithItem2D(DeferredBlock<?> block) {
        String name = block.getId().getPath();

        simpleBlock(
                block.get(),
                models().cross(name, modLoc("block/" + name))
                        .renderType("cutout")
        );

        itemModels()
                .withExistingParent(name, mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + name));
    }
}