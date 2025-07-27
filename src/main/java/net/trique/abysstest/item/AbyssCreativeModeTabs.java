package net.trique.abysstest.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.abysstest.AbyssTest;
import net.trique.abysstest.block.AbyssBlocks;

import java.util.function.Supplier;

public class AbyssCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AbyssTest.MODID);

    public static final Supplier<CreativeModeTab> ABYSS_ITEMS_TAB = CREATIVE_MODE_TAB.register("abyss_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AbyssItems.SOMETHING_INGOT.get()))
                    .title(Component.translatable("creativetab.abysstest.abyss_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(AbyssItems.RAW_SOMETHING);
                        output.accept(AbyssItems.SOMETHING_INGOT);
                        output.accept(AbyssItems.RAW_THING);
                        output.accept(AbyssItems.THING_INGOT);
                    }).build());

    public static final Supplier<CreativeModeTab> ABYSS_BLOCKS_TAB = CREATIVE_MODE_TAB.register("abyss_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AbyssBlocks.ABYSSTONE))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(AbyssTest.MODID, "abyss_items_tab"))
                    .title(Component.translatable("creativetab.abysstest.abyss_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(AbyssBlocks.ABYSSTONE);
                        output.accept(AbyssBlocks.SOMETHING_ORE);
                        output.accept(AbyssBlocks.SOMETHING_BLOCK);
                        output.accept(AbyssBlocks.THING_ORE);
                        output.accept(AbyssBlocks.THING_BLOCK);
                        output.accept(AbyssBlocks.NIGHT_NYLIUM);
                        output.accept(AbyssBlocks.AZURE_NYLIUM);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}