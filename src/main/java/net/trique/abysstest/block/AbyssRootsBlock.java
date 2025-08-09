package net.trique.abysstest.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RootsBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class AbyssRootsBlock extends RootsBlock {
    public AbyssRootsBlock(BlockBehaviour.Properties props) {
        super(props.noCollission().instabreak());
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(AbyssBlocks.NIGHT_NYLIUM.get())
                || state.is(AbyssBlocks.AMBER_NYLIUM.get())
                || state.is(AbyssBlocks.AZURE_NYLIUM.get())
                || state.is(AbyssBlocks.ABYSSTONE.get());
    }
}