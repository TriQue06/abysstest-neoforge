package net.trique.abysstest;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.trique.abysstest.block.AbyssBlocks;
import net.trique.abysstest.material.AbyssFluids;

@Mod(value = AbyssTest.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = AbyssTest.MODID, value = Dist.CLIENT)
public class AbyssTestClient {

    public AbyssTestClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        AbyssTest.LOGGER.info("HELLO FROM CLIENT SETUP");
        AbyssTest.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(AbyssBlocks.ABYSS_PORTAL.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AbyssBlocks.PURPLE_LAVA.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AbyssFluids.PURPLE_LAVA, RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(AbyssFluids.FLOWING_PURPLE_LAVA, RenderType.translucent());
        });
    }
}