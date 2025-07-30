package net.trique.abysstest;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.trique.abysstest.block.AbyssBlocks;

public class AbyssPortal {

    public static void register() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.QUARTZ_BLOCK) // portal çerçevesi: kuvars bloğu
                .lightWithItem(Items.FLINT_AND_STEEL) // yakma itemi: kuvars
                .destDimID(ResourceLocation.fromNamespaceAndPath(AbyssTest.MODID, "abyss"))
                .tintColor(128, 0, 255) // morumsu tint rengi (isteğe bağlı)
                .customPortalBlock(() -> AbyssBlocks.ABYSS_PORTAL.get()) // özel portal bloğu
                .registerPortal(); // portalı kaydet
    }
}
