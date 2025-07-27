package net.trique.abysstest.material;

import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.trique.abysstest.AbyssTest;

import java.util.function.Supplier;

public class AbyssFluidTypes {

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, AbyssTest.MODID);

    public static final Supplier<FluidType> PURPLE_LAVA = FLUID_TYPES.register("purple_lava",
            () -> new FluidType(FluidType.Properties.create()
                    .lightLevel(15)
                    .temperature(1300)
                    .density(3000)
                    .viscosity(6000)
            ) {});
}