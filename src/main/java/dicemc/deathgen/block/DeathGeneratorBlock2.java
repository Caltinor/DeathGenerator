package dicemc.deathgen.block;

import javax.annotation.Nullable;

import dicemc.deathgen.tile.DeathGeneratorBlockEntity2;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
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
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;

public class DeathGeneratorBlock2 extends Block implements EntityBlock{
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	
	public DeathGeneratorBlock2() {
		super(Properties.of(Material.STONE)
				.sound(SoundType.STONE)
				.lightLevel(state -> 0)
				.noOcclusion()
				.strength(2.0f));
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}
	
	public BlockState getStateForPlacement(BlockPlaceContext p_51377_) {
		return this.defaultBlockState().setValue(FACING, p_51377_.getHorizontalDirection().getOpposite());
    }
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_51385_) {
		p_51385_.add(FACING);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new DeathGeneratorBlockEntity2(pos, state);
	}
	
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!level.isClientSide) {
			BlockEntity tile = level.getBlockEntity(pos);
			if(tile != null && tile instanceof DeathGeneratorBlockEntity2 dgbe) {
				TranslatableComponent text = new TranslatableComponent("msg.dicemcdeathgen.current_energy", dgbe.getEnergyAmount());
				player.sendMessage(text, player.getUUID());
			}
			else {
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
				if (be instanceof DeathGeneratorBlockEntity2 generator) {
					generator.serverTick(level1, pos);
				}
			};
		}
	}

}
