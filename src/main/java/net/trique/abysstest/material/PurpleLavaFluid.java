package net.trique.abysstest.material;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.LavaFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.trique.abysstest.block.AbyssBlocks;
import net.trique.abysstest.item.AbyssItems;

import java.util.Optional;

public abstract class PurpleLavaFluid extends LavaFluid {

    @Override
    public ParticleOptions getDripParticle() {
        return ParticleTypes.FALLING_OBSIDIAN_TEAR;
    }

    @Override
    public FluidType getFluidType() {
        return AbyssFluidTypes.PURPLE_LAVA_TYPE.get();
    }

    @Override
    public Fluid getFlowing() {
        return AbyssFluids.FLOWING_PURPLE_LAVA;
    }

    @Override
    public Fluid getSource() {
        return AbyssFluids.PURPLE_LAVA;
    }

    @Override
    public BlockState createLegacyBlock(FluidState state) {
        return AbyssBlocks.PURPLE_LAVA.get()
                .defaultBlockState()
                .setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL_LAVA);
    }

    @Override
    public Item getBucket() {
        return AbyssItems.PURPLE_LAVA_BUCKET.get();
    }

    public boolean isSame(Fluid fluid) {
        return fluid == AbyssFluids.PURPLE_LAVA || fluid == AbyssFluids.FLOWING_PURPLE_LAVA;
    }

    @Override
    public void randomTick(Level level, BlockPos pos, FluidState state, RandomSource random) {
    }

    @Override
    public void animateTick(Level level, BlockPos pos, FluidState state, RandomSource random) {
    }

    public static class Flowing extends PurpleLavaFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }
        @Override public int  getAmount(FluidState state) { return state.getValue(LEVEL); }
        @Override public boolean isSource(FluidState state) { return false; }
    }

    public static class Source extends PurpleLavaFluid {
        @Override public int  getAmount(FluidState state) { return 8; }
        @Override public boolean isSource(FluidState state) { return true; }
    }
}