package net.trique.abysstest.material;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.trique.abysstest.AbyssTest;

import javax.annotation.Nullable;
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
                                public @Nullable ResourceLocation getRenderOverlayTexture(Minecraft mc) {
                                    // Underwater benzeri PNG tabanlı overlay (TAM yol + .png)
                                    return ResourceLocation.fromNamespaceAndPath(
                                            AbyssTest.MODID, "textures/misc/purple_lava_overlay.png");
                                }

                                // --- Kamera overlay: ekran üstünde sabit (dönmeyen/kaymayan) çizim ---
                                @Override
                                public void renderOverlay(Minecraft mc, com.mojang.blaze3d.vertex.PoseStack poseStack) {
                                    var tex = getRenderOverlayTexture(mc);
                                    if (tex == null || mc.player == null) return;

                                    com.mojang.blaze3d.systems.RenderSystem.setShader(net.minecraft.client.renderer.GameRenderer::getPositionTexShader);
                                    com.mojang.blaze3d.systems.RenderSystem.setShaderTexture(0, tex);

                                    // Opaklık ve parlaklık: istersen brightness yerine 1.0 kullan
                                    float alpha = 0.85F; // daha opak görünüm
                                    com.mojang.blaze3d.systems.RenderSystem.enableBlend();
                                    com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, alpha);

                                    // Ekran uzayında sabit quad: UV sabit (0..1), rotasyona bağlı scroll YOK
                                    org.joml.Matrix4f mat = poseStack.last().pose();
                                    var tesselator = com.mojang.blaze3d.vertex.Tesselator.getInstance();
                                    var buf = tesselator.begin(
                                            com.mojang.blaze3d.vertex.VertexFormat.Mode.QUADS,
                                            com.mojang.blaze3d.vertex.DefaultVertexFormat.POSITION_TEX
                                    );

                                    buf.addVertex(mat, -1.0F, -1.0F, -0.5F).setUv(1.0F, 1.0F);
                                    buf.addVertex(mat,  1.0F, -1.0F, -0.5F).setUv(0.0F, 1.0F);
                                    buf.addVertex(mat,  1.0F,  1.0F, -0.5F).setUv(0.0F, 0.0F);
                                    buf.addVertex(mat, -1.0F,  1.0F, -0.5F).setUv(1.0F, 0.0F);

                                    com.mojang.blaze3d.vertex.BufferUploader.drawWithShader(buf.buildOrThrow());
                                    com.mojang.blaze3d.systems.RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
                                    com.mojang.blaze3d.systems.RenderSystem.disableBlend();
                                }

                                // --- Sis (fog) rengini morlaştır ---
                                @Override
                                public org.joml.Vector3f modifyFogColor(
                                        net.minecraft.client.Camera camera, float partialTick,
                                        net.minecraft.client.multiplayer.ClientLevel level, int renderDistance,
                                        float darkenWorldAmount, org.joml.Vector3f fluidFogColor
                                ) {
                                    return new org.joml.Vector3f(0.70f, 0.25f, 0.85f);
                                }

                                // --- Sis mesafesi: 1.21.1'de RenderSystem ile kontrol et ---
                                @Override
                                public void modifyFogRender(
                                        net.minecraft.client.Camera camera,
                                        net.minecraft.client.renderer.FogRenderer.FogMode mode,
                                        float renderDistance, float partialTick,
                                        float nearDistance, float farDistance,
                                        com.mojang.blaze3d.shaders.FogShape shape
                                ) {
                                    float near = Math.min(nearDistance + 0.5f, 2.0f);
                                    float far  = Math.min(farDistance, 12.0f);
                                    com.mojang.blaze3d.systems.RenderSystem.setShaderFogStart(near);
                                    com.mojang.blaze3d.systems.RenderSystem.setShaderFogEnd(far);
                                    com.mojang.blaze3d.systems.RenderSystem.setShaderFogShape(shape);
                                }
                            });
                        }
                    });

    public static void register(IEventBus bus) {
        FLUID_TYPES.register(bus);
    }
}
