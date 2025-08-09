package net.trique.abysstest.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.trique.abysstest.AbyssTest;
import net.trique.abysstest.block.AbyssBlocks;
import net.trique.abysstest.item.AbyssItems;

import java.util.LinkedHashMap;

public class AbyssItemModelProvider extends ItemModelProvider {
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public AbyssItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AbyssTest.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(AbyssItems.RAW_SOMETHING.get());
        basicItem(AbyssItems.SOMETHING_INGOT.get());
        basicItem(AbyssItems.RAW_THING.get());
        basicItem(AbyssItems.THING_INGOT.get());
        basicItem(AbyssItems.PURPLE_LAVA_BUCKET.get());

        flatBlockItem(AbyssBlocks.NIGHT_ROOTS);
        flatBlockItem(AbyssBlocks.AZURE_ROOTS);
        flatBlockItem(AbyssBlocks.AMBER_ROOTS);
        flatBlockItem(AbyssBlocks.NIGHT_FUNGUS);
        flatBlockItem(AbyssBlocks.AZURE_FUNGUS);
        flatBlockItem(AbyssBlocks.AMBER_FUNGUS);
        flatBlockItem(AbyssBlocks.NIGHT_BUSH);
        flatBlockItem(AbyssBlocks.AZURE_BUSH);
        flatBlockItem(AbyssBlocks.AMBER_BUSH);
    }

    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld"))
                .texture("layer0", modLoc("item/" + item.getId().getPath()));
    }

    private void flatBlockItem(DeferredBlock<?> block) {
        String name = block.getId().getPath();
        getBuilder(name)
                .parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", modLoc("block/" + name));
    }
}