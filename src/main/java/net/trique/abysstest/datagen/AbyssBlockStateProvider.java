package net.trique.abysstest.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
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

        massBlockWithItem(AbyssBlocks.AZURE_NYLIUM_MASS);
        massBlockWithItem(AbyssBlocks.NIGHT_NYLIUM_MASS);
        massBlockWithItem(AbyssBlocks.AMBER_NYLIUM_MASS);

        nyliumBlockWithItem(AbyssBlocks.AZURE_NYLIUM, "abysstone");
        nyliumBlockWithItem(AbyssBlocks.NIGHT_NYLIUM, "abysstone");
        nyliumBlockWithItem(AbyssBlocks.AMBER_NYLIUM, "abysstone");

        crossBlockWithItem2D(AbyssBlocks.NIGHT_ROOTS);
        crossBlockWithItem2D(AbyssBlocks.AZURE_ROOTS);
        crossBlockWithItem2D(AbyssBlocks.AMBER_ROOTS);

        crossBlockWithItem2D(AbyssBlocks.NIGHT_FUNGUS);
        crossBlockWithItem2D(AbyssBlocks.AZURE_FUNGUS);
        crossBlockWithItem2D(AbyssBlocks.AMBER_FUNGUS);

        crossBlockWithItem2D(AbyssBlocks.NIGHT_BUSH);
        crossBlockWithItem2D(AbyssBlocks.AZURE_BUSH);
        crossBlockWithItem2D(AbyssBlocks.AMBER_BUSH);

        // 6-yönlü amethyst cluster’lar
        clusterBlockWithItem(AbyssBlocks.STRANGE_CLUSTER);
        clusterBlockWithItem(AbyssBlocks.WEIRD_CLUSTER);
        clusterBlockWithItem(AbyssBlocks.ODD_CLUSTER);

        // Not: Portal ve Liquid (purple_lava) için model üretmiyoruz.
    }

    /* ----- Helpers ----- */

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    /**
     * Nylium gibi üst/alt/yan dokuları farklı olan bloklar için.
     * baseBlockTextureName: bottom için kullanılacak doku (örn. "abysstone")
     */
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

    /**
     * Cross bitkiler: dünyada cross modeli, item’de 2D sprite.
     */
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

    private void clusterBlockWithItem(DeferredBlock<?> block) {
        String name = block.getId().getPath();

        // 1) Model: minecraft:block/cross parent + "cross" texture key
        var blockModel = models()
                .withExistingParent(name, mcLoc("block/cross"))
                .texture("cross", modLoc("block/" + name))
                .renderType("cutout");

        // 2) Blockstate: FACING (ve varsa WATERLOGGED) dâhil tüm stateleri tek modelle eşle
        getVariantBuilder(block.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(blockModel)
                        .build());

        // 3) Item modelini burada üretmiyoruz (elde 2D istiyoruz).
        //    ItemModelProvider'da: flatBlockItem(AbyssBlocks.STRANGE_CLUSTER); vb. kaldı.
    }

    private void massBlockWithItem(DeferredBlock<?> block) {
        String name = block.getId().getPath();                 // e.g. "azure_nylium_mass"
        String nyliumTex = name.replace("_nylium_mass", "_nylium");
        simpleBlockWithItem(
                block.get(),
                models().cubeAll(name, modLoc("block/" + nyliumTex))
        );
    }

}
