package dicemc.deathgen.item;

import dicemc.deathgen.block.DeathGeneratorBlock2;
import dicemc.deathgen.setup.Registration;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraftforge.common.util.Constants.BlockFlags;

public class UpgradeItem3 extends Item {

    public UpgradeItem3(Properties properties) {
        super(properties);
    }

    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer().isCrouching() && context.getLevel().getBlockState(context.getClickedPos()).getBlock() instanceof DeathGeneratorBlock2) {
            Direction facing = context.getLevel().getBlockState(context.getClickedPos()).getValue(DeathGeneratorBlock2.FACING);
            context.getLevel().setBlock(context.getClickedPos(), Registration.DEATH_GENERATOR_BLOCK3.get().defaultBlockState().setValue(DeathGeneratorBlock2.FACING, facing), BlockFlags.BLOCK_UPDATE);
            context.getItemInHand().shrink(1);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.FAIL;
    }
}
