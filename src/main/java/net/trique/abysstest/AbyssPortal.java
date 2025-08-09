package net.trique.abysstest;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.trique.abysstest.block.AbyssBlocks;
import net.trique.abysstest.block.AbyssPortalBlock;

public class AbyssPortal {

    public static void register() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(AbyssBlocks.ABYSSTONE.get())
                .lightWithFluid(Fluids.LAVA)
                .destDimID(ResourceLocation.fromNamespaceAndPath(AbyssTest.MODID, "abyss"))
                .returnDim(Level.NETHER.location(), true)
                .customPortalBlock(() -> (AbyssPortalBlock) AbyssBlocks.ABYSS_PORTAL.get())
                .registerPortal();
    }
}