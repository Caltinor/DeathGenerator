package dicemc.deathgen.datagen;

import dicemc.deathgen.DeathGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = DeathGenerator.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataCreator {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(new EntityTagsProvider(generator, helper));
            generator.addProvider(new LootTableProvider(generator));
        }
        if (event.includeClient()) {
            generator.addProvider(new ItemModelProvider(generator, helper));
            generator.addProvider(new BlockStateProvider(generator, helper));
        }
    }
}
