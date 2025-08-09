package net.trique.abysstest.material;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.trique.abysstest.AbyssTest;

import java.util.function.Consumer;

public final class AbyssFluidTypes {
    private AbyssFluidTypes() {}

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, AbyssTest.MODID);

    public static final DeferredHolder<FluidType, FluidType> PURPLE_LAVA_TYPE =
            FLUID_TYPES.register("purple_lava", () ->
                    new FluidType(FluidType.Properties.create()
                            .descriptionId("fluid." + AbyssTest.MODID + ".purple_lava")
                            .lightLevel(15)
                            .density(3000)
                            .viscosity(6000)
                            .temperature(1300)
                            .motionScale(0.0023)
                            .fallDistanceModifier(0.5f)
                            .canExtinguish(false)
                            .canDrown(false)
                            .canConvertToSource(false)
                            .canHydrate(false)
                            .canSwim(false)
                            .supportsBoating(false)
                    ) {
                        @SuppressWarnings("removal")
                        @Override
                        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                            consumer.accept(new IClientFluidTypeExtensions() {
                                @Override
                                public ResourceLocation getStillTexture() {
                                    return ResourceLocation.fromNamespaceAndPath(
                                            AbyssTest.MODID, "block/purple_lava_still");
                                }

                                @Override
                                public ResourceLocation getFlowingTexture() {
                                    return ResourceLocation.fromNamespaceAndPath(
                                            AbyssTest.MODID, "block/purple_lava_flow");
                                }

                                @Override
                                public ResourceLocation getOverlayTexture() {
                                    return ResourceLocation.fromNamespaceAndPath(
                                            AbyssTest.MODID, "misc/purple_lava_overlay");
                                }

                                @Override
                                public int getTintColor() {
                                    return 0xFFFFFFFF;
                                }
                            });
                        }
                    });

    public static void register(IEventBus bus) {
        FLUID_TYPES.register(bus);
    }
}