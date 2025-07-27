package net.trique.abysstest.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public class AbyssNyliumBlock extends Block implements BonemealableBlock {
    public static final MapCodec<AbyssNyliumBlock> CODEC = simpleCodec(AbyssNyliumBlock::new);

    public AbyssNyliumBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<AbyssNyliumBlock> codec() {
        return CODEC;
    }

    private static boolean canRemainNylium(BlockState state, LevelReader reader, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = reader.getBlockState(above);
        int lightBlock = LightEngine.getLightBlockInto(
                reader, state, pos, aboveState, above, Direction.UP, aboveState.getLightBlock(reader, above));
        return lightBlock < reader.getMaxLightLevel();
    }

    /** Zamanla abysstone'a dönüşme mantığı */
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canRemainNylium(state, level, pos)) {
            level.setBlockAndUpdate(pos, AbyssBlocks.ABYSSTONE.get().defaultBlockState());
        }
    }

    // --- Bonemeal ile çalışabilirlik ---

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return level.getBlockState(pos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
    }

    @Override
    public Type getType() {
        return Type.NEIGHBOR_SPREADER;
    }
}
