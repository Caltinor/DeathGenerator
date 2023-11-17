package dicemc.deathgen.block;

import dicemc.deathgen.tile.DeathGeneratorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class DeathGeneratorBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private final Tier tier;

    public DeathGeneratorBlock(Tier tier) {
        super(Properties.of()
                .sound(SoundType.STONE)
                .lightLevel(state -> 0)
                .noOcclusion()
                .strength(2.0f));
        this.tier = tier;
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DeathGeneratorBlockEntity(pos, state);
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity tile = level.getBlockEntity(pos);
            if (tile instanceof DeathGeneratorBlockEntity dgbe) {
                MutableComponent text = Component.translatable("msg.dicemcdeathgen.current_energy", dgbe.getEnergyAmount());
                player.sendSystemMessage(text);
            } else {
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide) return null;
        else {
            return (level1, pos, state1, be) -> {
                if (be instanceof DeathGeneratorBlockEntity generator) {
                    generator.serverTick(level1, pos);
                }
            };
        }
    }

    public Tier getTier() {
        return this.tier;
    }

    public enum Tier {
        TIER_1,
        TIER_2,
        TIER_3
    }
}
