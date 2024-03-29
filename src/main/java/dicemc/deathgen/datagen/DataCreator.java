package dicemc.deathgen.datagen;

import dicemc.deathgen.DeathGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeathGenerator.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataCreator {

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(false, new EntityTagsProvider(generator, event.getLookupProvider(), helper));
            generator.addProvider(false, new ModLootTableProvider(generator));
        }
        if (event.includeClient()) {
            generator.addProvider(true, new ItemModelProvider(generator, helper));
            generator.addProvider(true, new ModBlockStateProvider(generator, helper));
        }
    }
}
