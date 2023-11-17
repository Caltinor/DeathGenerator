package dicemc.deathgen.datagen;

import dicemc.deathgen.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModLootTableProvider extends LootTableProvider {


    public ModLootTableProvider(DataGenerator generator) {
        super(generator.getPackOutput(), Registration.BLOCKS.getEntries().stream().map(RegistryObject::getId).collect(Collectors.toSet()),
                List.of(new LootTableProvider.SubProviderEntry(BlockTable::new, LootContextParamSets.BLOCK)));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationContext context) {
        map.forEach((name, table) -> table.validate(context));
    }

    private static class BlockTable extends BlockLootSubProvider {
        public BlockTable() {
            super(Registration.BLOCKS.getEntries().stream().map(block -> block.get().asItem()).collect(Collectors.toSet()),
                    FeatureFlags.REGISTRY.allFlags());
        }

        @Override
        protected void generate() {
            for (RegistryObject<Block> entry : Registration.BLOCKS.getEntries()) {
                Block block = entry.get();
                this.dropSelf(block);
            }
        }

        @Nonnull
        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Registration.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        }
    }
}
