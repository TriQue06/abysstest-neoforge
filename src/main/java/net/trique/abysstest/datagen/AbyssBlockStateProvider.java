package net.trique.abysstest.datagen;

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

    @SuppressWarnings("removal")
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

        clusterBlockWithItem(AbyssBlocks.STRANGE_CLUSTER);
        clusterBlockWithItem(AbyssBlocks.WEIRD_CLUSTER);
        clusterBlockWithItem(AbyssBlocks.ODD_CLUSTER);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void nyliumBlockWithItem(DeferredBlock<?> block, String baseBlockTextureName) {
        String name = block.getId().getPath();
        models().cubeBottomTop(
                name,
                modLoc("block/" + name + "_side"),
                modLoc("block/" + baseBlockTextureName),
                modLoc("block/" + name)
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

    private void clusterBlockWithItem(DeferredBlock<?> block) {
        String name = block.getId().getPath();

        var blockModel = models()
                .withExistingParent(name, mcLoc("block/cross"))
                .texture("cross", modLoc("block/" + name))
                .renderType("cutout");

        getVariantBuilder(block.get())
                .forAllStatesExcept(state -> {
                    var facing = state.getValue(BlockStateProperties.FACING);
                    int xRot = 0;
                    int yRot = 0;

                    switch (facing) {
                        case UP -> { xRot = 0;   yRot = 0;   }
                        case DOWN -> { xRot = 180; yRot = 0;  }
                        case NORTH -> { xRot = 90;  yRot = 0;  }
                        case SOUTH -> { xRot = 90;  yRot = 180; }
                        case WEST  -> { xRot = 90;  yRot = 270; }
                        case EAST  -> { xRot = 90;  yRot = 90;  }
                    }

                    return ConfiguredModel.builder()
                            .modelFile(blockModel)
                            .rotationX(xRot)
                            .rotationY(yRot)
                            .build();
                }, BlockStateProperties.WATERLOGGED);
    }

    private void massBlockWithItem(DeferredBlock<?> block) {
        String name = block.getId().getPath();
        String nyliumTex = name.replace("_nylium_mass", "_nylium");
        simpleBlockWithItem(
                block.get(),
                models().cubeAll(name, modLoc("block/" + nyliumTex))
        );
    }
}