package com.ceetron.Redux.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class CultureVatBlock extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;

    private static final VoxelShape SHAPE = Block.box(0, 0, 0, 16, 32, 16);
    private static final VoxelShape SHAPE_BOTTOM =  Shapes.join(Block.box(1, 3, 1, 14, 13, 14), Block.box(0, 0, 0, 16, 3, 16), BooleanOp.OR);
    private static final VoxelShape SHAPE_TOP =  Shapes.join(Block.box(1, 0, 1, 14, 13, 14), Block.box(0, 8, 0, 16, 3, 16), BooleanOp.OR);


    public CultureVatBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

     /* BLOCK ENTITY */
    //Vanilla Copy
    public BlockState updateShape(BlockState state, Direction direction, BlockState state2, LevelAccessor levelAccessor, BlockPos pos1, BlockPos pos2) {
        DoubleBlockHalf doubleblockhalf = state.getValue(HALF);
        if (direction.getAxis() != Direction.Axis.Y || doubleblockhalf == DoubleBlockHalf.LOWER != (direction == Direction.UP) || state2.is(this) && state2.getValue(HALF) != doubleblockhalf) {
            return doubleblockhalf == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(levelAccessor, pos1) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, state2, levelAccessor, pos1, pos2);
        } else {
            return Blocks.AIR.defaultBlockState();
        }
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockPos, CollisionContext context) {
        if(state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            return SHAPE_TOP;
        } else{
            return SHAPE_BOTTOM;
        }
    }

    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity livingEntity, ItemStack stack) {
        BlockPos blockpos = pos.above();
        level.setBlock(blockpos, this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER), 3);
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext state) {
        BlockPos blockpos = state.getClickedPos();
        Level level = state.getLevel();
        if (blockpos.getY() < level.getMaxBuildHeight() - 1 && level.getBlockState(blockpos.above()).canBeReplaced(state)) {
            return this.defaultBlockState().setValue(FACING, state.getHorizontalDirection().getOpposite()).setValue(HALF, DoubleBlockHalf.LOWER);
        } else {
            return null;
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }
    
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return;
    }
}
