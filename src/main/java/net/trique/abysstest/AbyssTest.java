package net.trique.abysstest;

import net.trique.abysstest.block.AbyssBlocks;
import net.trique.abysstest.item.AbyssCreativeModeTabs;
import net.trique.abysstest.item.AbyssItems;
import net.trique.abysstest.sound.AbyssSounds;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(AbyssTest.MODID)
public class AbyssTest {
    public static final String MODID = "abysstest";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AbyssTest(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        AbyssCreativeModeTabs.register(modEventBus);
        AbyssItems.register(modEventBus);
        AbyssBlocks.register(modEventBus);
        AbyssSounds.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Things are working fine, probably.");
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Abyss Test works properly!");
    }
}