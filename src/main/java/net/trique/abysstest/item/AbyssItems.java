package net.trique.abysstest.item;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.abysstest.AbyssTest;
import net.trique.abysstest.material.AbyssFluids;

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
    public static final DeferredItem<Item> PURPLE_LAVA_BUCKET = ITEMS.register("purple_lava_bucket",
            () -> new BucketItem(AbyssFluids.PURPLE_LAVA, (new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}