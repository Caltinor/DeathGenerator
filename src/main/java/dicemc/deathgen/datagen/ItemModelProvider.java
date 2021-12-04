package dicemc.deathgen.datagen;

import dicemc.deathgen.DeathGenerator;
import dicemc.deathgen.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {

    public ItemModelProvider(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, DeathGenerator.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        for (RegistryObject<Item> entry : Registration.ITEMS.getEntries()) {
            Item item = entry.get();
            if (item instanceof BlockItem blockItem) {
                this.blockItem(blockItem);
            } else {
                this.generated(item);
            }
        }
    }

    private void blockItem(BlockItem item) {
        ResourceLocation location = item.getRegistryName();
        //noinspection ConstantConditions
        this.getBuilder(location.getPath())
                .parent(new AlwaysExistentModelFile(new ResourceLocation(location.getNamespace(), "block/" + location.getPath())));
    }

    private void generated(Item item) {
        //noinspection ConstantConditions
        String path = item.getRegistryName().getPath();
        this.getBuilder(path)
                .parent(this.getExistingFile(this.mcLoc("item/generated")))
                .texture("layer0", "item/" + path);
    }

    private static class AlwaysExistentModelFile extends ModelFile {

        protected AlwaysExistentModelFile(ResourceLocation location) {
            super(location);
        }

        @Override
        protected boolean exists() {
            return true;
        }
    }
}
