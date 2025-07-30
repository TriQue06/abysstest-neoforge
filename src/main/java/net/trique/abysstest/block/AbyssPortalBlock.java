package net.trique.abysstest.block;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.slf4j.Logger;

public class AbyssPortalBlock extends Block implements Portal {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    private static final Logger LOGGER = LogUtils.getLogger();
    protected static final VoxelShape X_AXIS_AABB = Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
    protected static final VoxelShape Z_AXIS_AABB = Block.box(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);

    public AbyssPortalBlock() {
        super(BlockBehaviour.Properties.of()
                .strength(-1.0F)
                .noCollission()
                .sound(SoundType.GLASS)
                .lightLevel(state -> 11));
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(AXIS) == Direction.Axis.Z ? Z_AXIS_AABB : X_AXIS_AABB;
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(100) == 0) {
            level.playLocalSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS,
                    0.5F, random.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int i = 0; i < 4; ++i) {
            double x = pos.getX() + random.nextDouble();
            double y = pos.getY() + random.nextDouble();
            double z = pos.getZ() + random.nextDouble();
            double dx = ((double) random.nextFloat() - 0.5) * 0.5;
            double dy = ((double) random.nextFloat() - 0.5) * 0.5;
            double dz = ((double) random.nextFloat() - 0.5) * 0.5;
            int j = random.nextInt(2) * 2 - 1;

            if (!level.getBlockState(pos.west()).is(this) && !level.getBlockState(pos.east()).is(this)) {
                x = pos.getX() + 0.5 + 0.25 * j;
                dx = random.nextFloat() * 2.0F * j;
            } else {
                z = pos.getZ() + 0.5 + 0.25 * j;
                dz = random.nextFloat() * 2.0F * j;
            }

            level.addParticle(ParticleTypes.PORTAL, x, y, z, dx, dy, dz);
        }
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide && entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, pos);
        }
    }

    @Override
    public int getPortalTransitionTime(ServerLevel level, Entity entity) {
        if (entity instanceof Player player) {
            return Math.max(1, level.getGameRules().getInt(
                    player.getAbilities().invulnerable
                            ? GameRules.RULE_PLAYERS_NETHER_PORTAL_CREATIVE_DELAY
                            : GameRules.RULE_PLAYERS_NETHER_PORTAL_DEFAULT_DELAY));
        }
        return 0;
    }

    @Override
    public DimensionTransition getPortalDestination(ServerLevel level, Entity entity, BlockPos pos) {
        ResourceLocation abyssID = ResourceLocation.fromNamespaceAndPath("abysstest", "abyss");
        ResourceKey<Level> target = ResourceKey.create(Registries.DIMENSION, abyssID);
        ServerLevel destLevel = level.getServer().getLevel(target);
        if (destLevel == null) return null;

        double scale = DimensionType.getTeleportationScale(level.dimensionType(), destLevel.dimensionType());
        BlockPos targetPos = destLevel.getSharedSpawnPos().atY((int) entity.getY());
        Vec3 targetVec = new Vec3(targetPos.getX(), targetPos.getY(), targetPos.getZ());

        return new DimensionTransition(destLevel, targetVec, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), DimensionTransition.PLAY_PORTAL_SOUND);
    }

    @Override
    public Transition getLocalTransition() {
        return Transition.CONFUSION;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return ItemStack.EMPTY;
    }
}
