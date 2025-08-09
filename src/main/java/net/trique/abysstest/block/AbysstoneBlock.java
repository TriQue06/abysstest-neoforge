package net.trique.abysstest.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AbysstoneBlock extends Block implements BonemealableBlock {
    public static final MapCodec<AbysstoneBlock> CODEC = simpleCodec(AbysstoneBlock::new);

    public AbysstoneBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<AbysstoneBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        // Üstü açık mı kontrol et
        if (!level.getBlockState(pos.above()).propagatesSkylightDown(level, pos)) {
            return false;
        }

        // 1 blok yarıçapında nylium var mı kontrol et
        for (BlockPos checkPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            BlockState neighborState = level.getBlockState(checkPos);
            if (neighborState.is(AbyssBlocks.AMBER_NYLIUM.get()) ||
                    neighborState.is(AbyssBlocks.AZURE_NYLIUM.get()) ||
                    neighborState.is(AbyssBlocks.NIGHT_NYLIUM.get())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true; // Her zaman başarılı olsun
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        boolean hasAmber = false;
        boolean hasAzure = false;
        boolean hasNight = false;

        // Çevrede hangi nylium tipleri var tespit et
        for (BlockPos checkPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            BlockState neighborState = level.getBlockState(checkPos);
            if (neighborState.is(AbyssBlocks.AMBER_NYLIUM.get())) hasAmber = true;
            if (neighborState.is(AbyssBlocks.AZURE_NYLIUM.get())) hasAzure = true;
            if (neighborState.is(AbyssBlocks.NIGHT_NYLIUM.get())) hasNight = true;

            if ((hasAmber && hasAzure) || (hasAmber && hasNight) || (hasAzure && hasNight)) {
                break; // En az iki farklı nylium bulunduysa erken çık
            }
        }

        // Çevredeki nylium tiplerine göre abysstone'u dönüştür
        if ((hasAmber && hasAzure) || (hasAmber && hasNight) || (hasAzure && hasNight)) {
            // Rastgele seç
            int choice = random.nextInt(3);
            if (choice == 0) level.setBlock(pos, AbyssBlocks.AMBER_NYLIUM.get().defaultBlockState(), 3);
            else if (choice == 1) level.setBlock(pos, AbyssBlocks.AZURE_NYLIUM.get().defaultBlockState(), 3);
            else level.setBlock(pos, AbyssBlocks.NIGHT_NYLIUM.get().defaultBlockState(), 3);
        } else if (hasAmber) {
            level.setBlock(pos, AbyssBlocks.AMBER_NYLIUM.get().defaultBlockState(), 3);
        } else if (hasAzure) {
            level.setBlock(pos, AbyssBlocks.AZURE_NYLIUM.get().defaultBlockState(), 3);
        } else if (hasNight) {
            level.setBlock(pos, AbyssBlocks.NIGHT_NYLIUM.get().defaultBlockState(), 3);
        }
    }

    @Override
    public BonemealableBlock.Type getType() {
        return Type.NEIGHBOR_SPREADER;
    }
}
