package dicemc.deathgen.datagen;

import dicemc.deathgen.DeathGenerator;
import dicemc.deathgen.setup.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class EntityTagsProvider extends EntityTypeTagsProvider {

    public EntityTagsProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> thing, @Nullable ExistingFileHelper helper) {
        super(generator.getPackOutput(), thing, DeathGenerator.MOD_ID, helper);
    }

    @SuppressWarnings("unchecked")
	@Override
    protected void addTags(HolderLookup.Provider p_255894_) {
        this.tag(Registration.ANIMALS).add(
                EntityType.BAT,
                EntityType.BEE,
                EntityType.CAT,
                EntityType.CHICKEN,
                EntityType.COD,
                EntityType.COW,
                EntityType.DOLPHIN,
                EntityType.DONKEY,
                EntityType.FOX,
                EntityType.GLOW_SQUID,
                EntityType.GOAT,
                EntityType.HORSE,
                EntityType.IRON_GOLEM,
                EntityType.LLAMA,
                EntityType.MOOSHROOM,
                EntityType.MULE,
                EntityType.OCELOT,
                EntityType.PANDA,
                EntityType.PARROT,
                EntityType.PIG,
                EntityType.POLAR_BEAR,
                EntityType.PUFFERFISH,
                EntityType.RABBIT,
                EntityType.SALMON,
                EntityType.SHEEP,
                EntityType.SNOW_GOLEM,
                EntityType.SQUID,
                EntityType.STRIDER,
                EntityType.TRADER_LLAMA,
                EntityType.TROPICAL_FISH,
                EntityType.TURTLE,
                EntityType.WOLF
        );

        //noinspection unchecked
        this.tag(Registration.MONSTERS).addTags(EntityTypeTags.SKELETONS).add(
                EntityType.BLAZE,
                EntityType.CAVE_SPIDER,
                EntityType.CREEPER,
                EntityType.DROWNED,
                EntityType.ELDER_GUARDIAN,
                EntityType.ENDER_DRAGON,
                EntityType.ENDERMAN,
                EntityType.ENDERMITE,
                EntityType.GHAST,
                EntityType.GIANT,
                EntityType.GUARDIAN,
                EntityType.HOGLIN,
                EntityType.HUSK,
                EntityType.MAGMA_CUBE,
                EntityType.PHANTOM,
                EntityType.PIGLIN,
                EntityType.PIGLIN_BRUTE,
                EntityType.SHULKER,
                EntityType.SILVERFISH,
                EntityType.SKELETON_HORSE,
                EntityType.SLIME,
                EntityType.SPIDER,
                EntityType.VEX,
                EntityType.WITCH,
                EntityType.WITHER,
                EntityType.ZOGLIN,
                EntityType.ZOMBIE,
                EntityType.ZOMBIE_HORSE,
                EntityType.ZOMBIE_VILLAGER,
                EntityType.ZOMBIFIED_PIGLIN
        );

        //noinspection unchecked
        this.tag(Registration.ILLAGERS).addTags(EntityTypeTags.RAIDERS);

        this.tag(Registration.VILLAGERS).add(
                EntityType.IRON_GOLEM,
                EntityType.VILLAGER,
                EntityType.WANDERING_TRADER
        );
    }
}
