package dicemc.deathgen.item;

import dicemc.deathgen.block.DeathGeneratorBlock;
import dicemc.deathgen.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.Constants.BlockFlags;

public class UpgradeItem extends Item {

    private final DeathGeneratorBlock.Tier tier;

    public UpgradeItem(Properties properties, DeathGeneratorBlock.Tier fromTier) {
        super(properties);
        this.tier = fromTier;
    }

    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        BlockState state = level.getBlockState(pos);
        if (context.getPlayer().isCrouching() && state.getBlock() instanceof DeathGeneratorBlock deathGeneratorBlock
                && deathGeneratorBlock.getTier() == this.tier) {
            Direction facing = state.getValue(DeathGeneratorBlock.FACING);
            BlockState upgradedState = this.getUpgradedBlock(deathGeneratorBlock, facing);

            if (upgradedState == null) {
                return InteractionResult.FAIL;
            }

            level.setBlock(pos, upgradedState, BlockFlags.BLOCK_UPDATE);
            context.getItemInHand().shrink(1);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.FAIL;
    }

    private BlockState getUpgradedBlock(DeathGeneratorBlock block, Direction facing) {
        BlockState state;
        switch (block.getTier()) {
            case TIER_1 -> state = Registration.DEATH_GENERATOR_BLOCK2.get().defaultBlockState();
            case TIER_2 -> state = Registration.DEATH_GENERATOR_BLOCK3.get().defaultBlockState();
            default -> state = null;
        }

        if (state != null) {
            state = state.setValue(DeathGeneratorBlock.FACING, facing);
        }

        return state;
    }
}
