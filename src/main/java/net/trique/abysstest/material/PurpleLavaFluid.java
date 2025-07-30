package net.trique.abysstest.material;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.*;
import net.trique.abysstest.block.AbyssBlocks;
import net.neoforged.neoforge.event.EventHooks;
import net.neoforged.neoforge.fluids.FluidType;


import javax.annotation.Nullable;
import java.util.Optional;

public abstract class PurpleLavaFluid extends FlowingFluid {
    public static final float MIN_LEVEL_CUTOFF = 0.44444445F;

    @Override
    public FluidType getFluidType() {
        return AbyssFluidTypes.PURPLE_LAVA.get();
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
    public Item getBucket() {
        return null;
    }

    @Override
    public void animateTick(Level level, BlockPos pos, FluidState state, RandomSource random) {
        BlockPos above = pos.above();
        if (level.getBlockState(above).isAir() && !level.getBlockState(above).isSolidRender(level, above)) {
            if (random.nextInt(100) == 0) {
                double x = pos.getX() + random.nextDouble();
                double y = pos.getY() + 1.0;
                double z = pos.getZ() + random.nextDouble();
                level.addParticle(ParticleTypes.LAVA, x, y, z, 0.0, 0.0, 0.0);
                level.playLocalSound(x, y, z, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
            if (random.nextInt(200) == 0) {
                level.playLocalSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.LAVA_AMBIENT, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
            }
        }
    }

    @Override
    public void randomTick(Level level, BlockPos pos, FluidState state, RandomSource random) {
        if (level.getGameRules().getBoolean(GameRules.RULE_DOFIRETICK)) {
            int i = random.nextInt(3);
            if (i > 0) {
                BlockPos target = pos;
                for (int j = 0; j < i; ++j) {
                    target = target.offset(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
                    if (!level.isLoaded(target)) return;
                    BlockState blockstate = level.getBlockState(target);
                    if (blockstate.isAir() && hasFlammableNeighbours(level, target)) {
                        BlockState fireState = BaseFireBlock.getState(level, target);
                        level.setBlockAndUpdate(target, EventHooks.fireFluidPlaceBlockEvent(level, target, pos, fireState));
                        return;
                    } else if (blockstate.blocksMotion()) {
                        return;
                    }
                }
            } else {
                for (int k = 0; k < 3; ++k) {
                    BlockPos side = pos.offset(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                    if (!level.isLoaded(side)) return;
                    if (level.isEmptyBlock(side.above()) && isFlammable(level, side, Direction.UP)) {
                        BlockState fireState = BaseFireBlock.getState(level, side.above());
                        level.setBlockAndUpdate(side.above(), EventHooks.fireFluidPlaceBlockEvent(level, side.above(), pos, fireState));
                    }
                }
            }
        }
    }

    private boolean hasFlammableNeighbours(LevelReader level, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            if (isFlammable(level, pos.relative(dir), dir.getOpposite())) {
                return true;
            }
        }
        return false;
    }

    private boolean isFlammable(LevelReader level, BlockPos pos, Direction face) {
        if (!level.hasChunkAt(pos) || pos.getY() < level.getMinBuildHeight() || pos.getY() >= level.getMaxBuildHeight()) {
            return false;
        }
        BlockState state = level.getBlockState(pos);
        return state.ignitedByLava() && state.isFlammable(level, pos, face);
    }

    @Nullable
    @Override
    public ParticleOptions getDripParticle() {
        return ParticleTypes.DRIPPING_LAVA;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor level, BlockPos pos, BlockState state) {
        fizz(level, pos);
    }

    private void fizz(LevelAccessor level, BlockPos pos) {
        level.levelEvent(1501, pos, 0);
    }

    @Override
    protected boolean canConvertToSource(Level level) {
        return level.getGameRules().getBoolean(GameRules.RULE_LAVA_SOURCE_CONVERSION);
    }

    @Override
    public int getSlopeFindDistance(LevelReader level) {
        return level.dimensionType().ultraWarm() ? 4 : 2;
    }

    @Override
    public int getDropOff(LevelReader level) {
        return level.dimensionType().ultraWarm() ? 1 : 2;
    }

    @Override
    public int getTickDelay(LevelReader level) {
        return level.dimensionType().ultraWarm() ? 10 : 30;
    }

    @Override
    public int getSpreadDelay(Level level, BlockPos pos, FluidState currentState, FluidState newState) {
        int delay = getTickDelay(level);
        if (!currentState.isEmpty() && !newState.isEmpty() &&
                !currentState.getValue(FALLING) && !newState.getValue(FALLING) &&
                newState.getHeight(level, pos) > currentState.getHeight(level, pos) &&
                level.getRandom().nextInt(4) != 0) {
            delay *= 4;
        }
        return delay;
    }

    @Override
    protected void spreadTo(LevelAccessor level, BlockPos pos, BlockState blockState, Direction direction, FluidState fluidState) {
        if (direction == Direction.DOWN) {
            FluidState other = level.getFluidState(pos);
            if (this.is(FluidTags.LAVA) && other.is(FluidTags.WATER)) {
                if (blockState.getBlock() instanceof LiquidBlock) {
                    level.setBlock(pos, EventHooks.fireFluidPlaceBlockEvent(level, pos, pos, Blocks.STONE.defaultBlockState()), 3);
                }
                fizz(level, pos);
                return;
            }
        }
        super.spreadTo(level, pos, blockState, direction, fluidState);
    }

    @Override
    public boolean canBeReplacedWith(FluidState fluidState, BlockGetter blockReader, BlockPos pos, Fluid fluid, Direction direction) {
        return fluidState.getHeight(blockReader, pos) >= MIN_LEVEL_CUTOFF && fluid.is(FluidTags.WATER);
    }

    @Override
    public boolean isSame(Fluid fluid) {
        return fluid == AbyssFluids.PURPLE_LAVA || fluid == AbyssFluids.FLOWING_PURPLE_LAVA;
    }

    @Override
    public BlockState createLegacyBlock(FluidState state) {
        return AbyssBlocks.PURPLE_LAVA.get().defaultBlockState().setValue(LiquidBlock.LEVEL, getLegacyLevel(state));
    }

    @Override
    protected boolean isRandomlyTicking() {
        return true;
    }

    @Override
    protected float getExplosionResistance() {
        return 100.0F;
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return Optional.of(SoundEvents.BUCKET_FILL_LAVA);
    }

    public static class Flowing extends PurpleLavaFluid {
        @Override
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> builder) {
            super.createFluidStateDefinition(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getAmount(FluidState state) {
            return state.getValue(LEVEL);
        }

        @Override
        public boolean isSource(FluidState state) {
            return false;
        }
    }

    public static class Source extends PurpleLavaFluid {
        @Override
        public int getAmount(FluidState state) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState state) {
            return true;
        }
    }
}
