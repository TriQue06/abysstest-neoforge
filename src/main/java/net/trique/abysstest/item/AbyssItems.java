package net.trique.abysstest.item;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.abysstest.AbyssTest;

public class AbyssItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AbyssTest.MODID);

    public static final DeferredItem<Item> RAW_SOMETHING = ITEMS.register("raw_something",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SOMETHING_INGOT = ITEMS.register("something_ingot",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_THING = ITEMS.register("raw_thing",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> THING_INGOT = ITEMS.register("thing_ingot",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}