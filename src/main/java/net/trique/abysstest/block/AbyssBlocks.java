package net.trique.abysstest.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.abysstest.AbyssTest;
import net.trique.abysstest.item.AbyssItems;
import net.trique.abysstest.material.AbyssFluids;

import java.util.function.Supplier;

public class AbyssBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(AbyssTest.MODID);

    public static final DeferredBlock<Block> ABYSSTONE = registerBlock("abysstone",
            () -> new AbysstoneBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE).instrument(NoteBlockInstrument.BASEDRUM).strength(3f).requiresCorrectToolForDrops().sound(SoundType.NETHERRACK)));

    public static final DeferredBlock<Block> SOMETHING_ORE = registerBlock("something_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0f, 3.0f).requiresCorrectToolForDrops().sound(SoundType.NETHER_GOLD_ORE)));

    public static final DeferredBlock<Block> SOMETHING_BLOCK = registerBlock("something_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(5F, 6F).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<Block> THING_ORE = registerBlock("thing_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE).instrument(NoteBlockInstrument.BASEDRUM).strength(3.0f, 3.0f).requiresCorrectToolForDrops().sound(SoundType.NETHER_GOLD_ORE)));

    public static final DeferredBlock<Block> THING_BLOCK = registerBlock("thing_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PINK).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(5F, 6F).requiresCorrectToolForDrops().sound(SoundType.METAL)));

    public static final DeferredBlock<Block> AZURE_NYLIUM = registerBlock("azure_nylium",
            () -> new AbyssNyliumBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.4F)
                    .sound(SoundType.NYLIUM)
                    .randomTicks()));

    public static final DeferredBlock<Block> NIGHT_NYLIUM = registerBlock("night_nylium",
            () -> new AbyssNyliumBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.4F)
                    .sound(SoundType.NYLIUM)
                    .randomTicks()));

    public static final DeferredBlock<Block> AMBER_NYLIUM = registerBlock("amber_nylium",
            () -> new AbyssNyliumBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.4F)
                    .sound(SoundType.NYLIUM)
                    .randomTicks()));

    public static final DeferredBlock<Block> ABYSS_PORTAL = registerBlock("abyss_portal",
            () -> new AbyssPortalBlock(BlockBehaviour.Properties.of()
                    .noCollission()
                    .noOcclusion()
                    .strength(-1.0F)
                    .lightLevel(state -> 12)
                    .pushReaction(net.minecraft.world.level.material.PushReaction.BLOCK)
                    .noLootTable()
            ));

    public static final DeferredBlock<LiquidBlock> PURPLE_LAVA = BLOCKS.register("purple_lava",
            () -> new LiquidBlock(AbyssFluids.PURPLE_LAVA,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.COLOR_PURPLE)
                            .replaceable()
                            .noCollission()
                            .randomTicks()
                            .strength(100.0F)
                            .pushReaction(PushReaction.DESTROY)
                            .noLootTable()
                            .liquid()
                            .lightLevel(state -> 12)
                            .sound(SoundType.EMPTY)));

    public static final DeferredBlock<Block> NIGHT_ROOTS = registerBlock("night_roots",
            () -> new AbyssRootsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.ROOTS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .lightLevel(state -> 5)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> AZURE_ROOTS = registerBlock("azure_roots",
            () -> new AbyssRootsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.ROOTS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .lightLevel(state -> 5)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> AMBER_ROOTS = registerBlock("amber_roots",
            () -> new AbyssRootsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.ROOTS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .lightLevel(state -> 5)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> NIGHT_FUNGUS = registerBlock("night_fungus",
            () -> new AbyssRootsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.FUNGUS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .lightLevel(state -> 5)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> AZURE_FUNGUS = registerBlock("azure_fungus",
            () -> new AbyssRootsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.FUNGUS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .lightLevel(state -> 5)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> AMBER_FUNGUS = registerBlock("amber_fungus",
            () -> new AbyssRootsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.FUNGUS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .lightLevel(state -> 5)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> NIGHT_BUSH = registerBlock("night_bush",
            () -> new AbyssRootsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.ROOTS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .lightLevel(state -> 5)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> AZURE_BUSH = registerBlock("azure_bush",
            () -> new AbyssRootsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.ROOTS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .lightLevel(state -> 5)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> AMBER_BUSH = registerBlock("amber_bush",
            () -> new AbyssRootsBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BLUE)
                    .replaceable()
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.ROOTS)
                    .offsetType(BlockBehaviour.OffsetType.XZ)
                    .lightLevel(state -> 5)
                    .pushReaction(PushReaction.DESTROY)));

    public static final DeferredBlock<Block> STRANGE_CLUSTER = registerBlock("strange_cluster",
            () -> new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel(state -> 12).pushReaction(PushReaction.DESTROY).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> WEIRD_CLUSTER = registerBlock("weird_cluster",
            () -> new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel(state -> 12).pushReaction(PushReaction.DESTROY).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> ODD_CLUSTER = registerBlock("odd_cluster",
            () -> new AmethystClusterBlock(7.0F, 3.0F, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).forceSolidOn().noOcclusion().sound(SoundType.AMETHYST_CLUSTER).strength(1.5F).lightLevel(state -> 12).pushReaction(PushReaction.DESTROY).requiresCorrectToolForDrops()));

    public static final DeferredBlock<Block> AZURE_NYLIUM_MASS = registerBlock("azure_nylium_mass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_LIGHT_BLUE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.4F)
                    .sound(SoundType.NYLIUM)
                    .lightLevel(state -> 12)
                    .randomTicks()));

    public static final DeferredBlock<Block> NIGHT_NYLIUM_MASS = registerBlock("night_nylium_mass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_PURPLE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.4F)
                    .sound(SoundType.NYLIUM)
                    .lightLevel(state -> 12)
                    .randomTicks()));

    public static final DeferredBlock<Block> AMBER_NYLIUM_MASS = registerBlock("amber_nylium_mass",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_ORANGE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops()
                    .strength(0.4F)
                    .sound(SoundType.NYLIUM)
                    .lightLevel(state -> 12)
                    .randomTicks()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        AbyssItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}