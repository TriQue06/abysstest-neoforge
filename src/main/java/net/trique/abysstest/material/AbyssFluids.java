package net.trique.abysstest.material;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.trique.abysstest.AbyssTest;

public class AbyssFluids {

    public static final FlowingFluid FLOWING_PURPLE_LAVA = register("flowing_purple_lava", new PurpleLavaFluid.Flowing());
    public static final FlowingFluid PURPLE_LAVA = register("purple_lava", new PurpleLavaFluid.Source());

    private static <T extends Fluid> T register(String key, T fluid) {
        return Registry.register(BuiltInRegistries.FLUID, key, fluid);
    }

    public static void bootstrap() {
        for (Fluid fluid : BuiltInRegistries.FLUID) {
            for (var fluidState : fluid.getStateDefinition().getPossibleStates()) {
                Fluid.FLUID_STATE_REGISTRY.add(fluidState);
            }
        }
    }
}