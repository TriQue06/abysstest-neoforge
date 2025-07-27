package net.trique.abysstest.material;

import com.google.common.collect.UnmodifiableIterator;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;

public class AbyssFluids {
    public static final FlowingFluid FLOWING_PURPLE_LAVA = register("flowing_purple_lava", new PurpleLavaFluid.Flowing());
    public static final FlowingFluid PURPLE_LAVA = register("purple_lava", new PurpleLavaFluid.Source());

    private static <T extends Fluid> T register(String key, T fluid) {
        return (T)(Registry.register(BuiltInRegistries.FLUID, key, fluid));
    }

    static {
        for(Fluid fluid : BuiltInRegistries.FLUID) {
            UnmodifiableIterator var2 = fluid.getStateDefinition().getPossibleStates().iterator();

            while(var2.hasNext()) {
                FluidState fluidstate = (FluidState)var2.next();
                Fluid.FLUID_STATE_REGISTRY.add(fluidstate);
            }
        }

    }
}