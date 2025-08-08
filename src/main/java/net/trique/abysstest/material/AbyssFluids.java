package net.trique.abysstest.material;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.trique.abysstest.AbyssTest;

import java.util.function.Supplier;

public class AbyssFluids {

    private static final FluidType PURPLE_LAVA_TYPE = new FluidType(FluidType.Properties.create()
            .density(3000)
            .viscosity(6000)
            .temperature(1300)
            .lightLevel(10)
            .canExtinguish(false)
            .supportsBoating(false)) {};

    public static FluidType getPurpleLavaType() {
        return PURPLE_LAVA_TYPE;
    }

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(Registries.FLUID, AbyssTest.MODID);

    public static final Supplier<Fluid> PURPLE_LAVA_STILL =
            FLUIDS.register("purple_lava_still", PurpleLavaFluid.Source::new);

    public static final Supplier<Fluid> PURPLE_LAVA_FLOW =
            FLUIDS.register("purple_lava_flow", PurpleLavaFluid.Flowing::new);
}
