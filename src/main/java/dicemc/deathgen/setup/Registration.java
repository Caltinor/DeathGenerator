package dicemc.deathgen.setup;

import dicemc.deathgen.DeathGenerator;
import dicemc.deathgen.block.DeathGeneratorBlock;
import dicemc.deathgen.item.UpgradeItem;
import dicemc.deathgen.tile.DeathGeneratorBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DeathGenerator.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DeathGenerator.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DeathGenerator.MOD_ID);
    //public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, DeathGenerator.MOD_ID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
        //CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static void addToTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTab().equals(BuiltInRegistries.CREATIVE_MODE_TAB.get(CreativeModeTabs.REDSTONE_BLOCKS))) {
            for (RegistryObject<Item> item : ITEMS.getEntries()) {
                event.accept(item::get);
            }
        }
    }

    //BLOCKS
    public static final RegistryObject<DeathGeneratorBlock> DEATH_GENERATOR_BLOCK = BLOCKS.register("pumpkin_of_pain", () -> new DeathGeneratorBlock(DeathGeneratorBlock.Tier.TIER_1));
    public static final RegistryObject<DeathGeneratorBlock> DEATH_GENERATOR_BLOCK2 = BLOCKS.register("pumpkin_of_suffering", () -> new DeathGeneratorBlock(DeathGeneratorBlock.Tier.TIER_2));
    public static final RegistryObject<DeathGeneratorBlock> DEATH_GENERATOR_BLOCK3 = BLOCKS.register("pumpkin_of_hellfire", () -> new DeathGeneratorBlock(DeathGeneratorBlock.Tier.TIER_3));

    //ITEMS
    public static final RegistryObject<Item> DEATH_GENERATOR = ITEMS.register("pumpkin_of_pain", () -> new BlockItem(DEATH_GENERATOR_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEATH_GENERATOR2 = ITEMS.register("pumpkin_of_suffering", () -> new BlockItem(DEATH_GENERATOR_BLOCK2.get(), new Item.Properties()));
    public static final RegistryObject<Item> DEATH_GENERATOR3 = ITEMS.register("pumpkin_of_hellfire", () -> new BlockItem(DEATH_GENERATOR_BLOCK3.get(), new Item.Properties()));

    public static final RegistryObject<Item> TIER2_UPGRADE = ITEMS.register("upgrade_2", () -> new UpgradeItem(new Item.Properties(), DeathGeneratorBlock.Tier.TIER_1));
    public static final RegistryObject<Item> TIER3_UPGRADE = ITEMS.register("upgrade_3", () -> new UpgradeItem(new Item.Properties(), DeathGeneratorBlock.Tier.TIER_2));

    //BLOCK ENTITIES
    public static final RegistryObject<BlockEntityType<DeathGeneratorBlockEntity>> DEATH_GENERATOR_TYPE = BLOCKENTITIES.register("death_generator", () -> BlockEntityType.Builder.of(DeathGeneratorBlockEntity::new, DEATH_GENERATOR_BLOCK.get(), DEATH_GENERATOR_BLOCK2.get(), DEATH_GENERATOR_BLOCK3.get()).build(null));

    //CONTAINERS
	/*public static final RegistryObject<MenuType<DeathGeneratorContainer>> GENERATOR_CONTAINER = CONTAINERS.register("death_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
		BlockPos pos = data.readBlockPos();
		Level world = inv.player.getCommandSenderWorld();
		return new DeathGeneratorContainer(windowId, world, pos, inv);
	}));*/

    //TAGS
    public static final TagKey<EntityType<?>> ANIMALS = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(DeathGenerator.MOD_ID, "animals"));
    public static final TagKey<EntityType<?>> MONSTERS = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(DeathGenerator.MOD_ID, "monsters"));
    public static final TagKey<EntityType<?>> ILLAGERS = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(DeathGenerator.MOD_ID, "illagers"));
    public static final TagKey<EntityType<?>> VILLAGERS = TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(DeathGenerator.MOD_ID, "villagers"));
}
