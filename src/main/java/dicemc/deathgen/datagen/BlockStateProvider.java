package dicemc.deathgen.datagen;

import dicemc.deathgen.DeathGenerator;
import dicemc.deathgen.setup.Registration;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.VariantBlockStateBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, DeathGenerator.MOD_ID, helper);
    }

    @SuppressWarnings("deprecation")
	@Override
    protected void registerStatesAndModels() {
        for (RegistryObject<Block> entry : Registration.BLOCKS.getEntries()) {
            Block block = entry.get();
            if (block.getStateDefinition().getProperties().contains(BlockStateProperties.HORIZONTAL_FACING)) {
                VariantBlockStateBuilder builder = this.getVariantBuilder(block);
                for (Direction direction : BlockStateProperties.HORIZONTAL_FACING.getPossibleValues()) {
                    ResourceLocation registryName = block.builtInRegistryHolder().unwrapKey().get().location();
                    //noinspection ConstantConditions
                    ModelFile.ExistingModelFile model = this.models().getExistingFile(new ResourceLocation(registryName.getNamespace(), "block/" + registryName.getPath()));
                    builder.partialState().with(BlockStateProperties.HORIZONTAL_FACING, direction)
                            .addModels(new ConfiguredModel(model, 0, (int) direction.getOpposite().toYRot(), false));
                }
            } else {
                this.simpleBlock(block);
            }
        }
    }
}
