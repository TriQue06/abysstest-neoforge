package net.trique.abysstest.material;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.BlockGetter;
import net.neoforged.neoforge.fluids.FluidType;
import net.trique.abysstest.block.AbyssBlocks;

public abstract class PurpleLavaFluid extends FlowingFluid {

    @Override
    public Fluid getFlowing() {
        return AbyssFluids.PURPLE_LAVA_FLOW.get();
    }

    @Override
    public Fluid getSource() {
        return AbyssFluids.PURPLE_LAVA_STILL.get();
    }

    @Override
    public FluidType getFluidType() {
        return AbyssFluids.getPurpleLavaType();
    }

    @Override
    public boolean isSame(Fluid fluid) {
        return fluid == getFlowing() || fluid == getSource();
    }

    @Override
    protected BlockState createLegacyBlock(FluidState state) {
        return AbyssBlocks.PURPLE_LAVA.get()
                .defaultBlockState()
                .setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    public int getAmount(FluidState state) {
        return isSource(state) ? 8 : state.getValue(LEVEL);
    }

    @Override
    public boolean isSource(FluidState state) {
        return state.getType() == getSource();
    }

    @Override
    public int getTickDelay(LevelReader level) {
        return 30;
    }

    @Override
    public int getSlopeFindDistance(LevelReader level) {
        return 4;
    }

    @Override
    public int getDropOff(LevelReader level) {
        return 1;
    }

    @Override
    protected boolean canConvertToSource(Level level) {
        return false;
    }

    @Override
    protected float getExplosionResistance() {
        return 100f;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {}

    @Override
    public void animateTick(Level level, BlockPos pos, FluidState state, RandomSource random) {}

    public static class Source extends PurpleLavaFluid {
        @Override
        public Item getBucket() {
            return null;
        }

        @Override
        public boolean canBeReplacedWith(FluidState fluidState, BlockGetter level, BlockPos pos, Fluid fluid, Direction direction) {
            return false;
        }
    }

    public static class Flowing extends PurpleLavaFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public Item getBucket() {
            return null;
        }

        @Override
        public boolean canBeReplacedWith(FluidState fluidState, BlockGetter level, BlockPos pos, Fluid fluid, Direction direction) {
            return false;
        }
    }
}