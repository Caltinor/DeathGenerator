package dicemc.deathgen.setup;

import dicemc.deathgen.DeathGenerator;
import dicemc.deathgen.block.DeathGeneratorBlock;
import dicemc.deathgen.block.DeathGeneratorBlock2;
import dicemc.deathgen.block.DeathGeneratorBlock3;
import dicemc.deathgen.item.UpgradeItem2;
import dicemc.deathgen.item.UpgradeItem3;
import dicemc.deathgen.tile.DeathGeneratorBlockEntity;
import dicemc.deathgen.tile.DeathGeneratorBlockEntity2;
import dicemc.deathgen.tile.DeathGeneratorBlockEntity3;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DeathGenerator.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DeathGenerator.MOD_ID);
	public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, DeathGenerator.MOD_ID);
	//public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, DeathGenerator.MOD_ID);
	
	public static void init() {
		BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		BLOCKENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
		//CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	//BLOCKS
	public static final RegistryObject<DeathGeneratorBlock> DEATH_GENERATOR_BLOCK = BLOCKS.register("pumpkin_of_pain", () -> new DeathGeneratorBlock());
	public static final RegistryObject<DeathGeneratorBlock2> DEATH_GENERATOR_BLOCK2 = BLOCKS.register("pumpkin_of_suffering", () -> new DeathGeneratorBlock2());
	public static final RegistryObject<DeathGeneratorBlock3> DEATH_GENERATOR_BLOCK3 = BLOCKS.register("pumpkin_of_hellfire", () -> new DeathGeneratorBlock3());
	
	//ITEMS
	public static final RegistryObject<Item> DEATH_GENERATOR = ITEMS.register("pumpkin_of_pain", () -> new BlockItem(DEATH_GENERATOR_BLOCK.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
	public static final RegistryObject<Item> DEATH_GENERATOR2 = ITEMS.register("pumpkin_of_suffering", () -> new BlockItem(DEATH_GENERATOR_BLOCK2.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
	public static final RegistryObject<Item> DEATH_GENERATOR3 = ITEMS.register("pumpkin_of_hellfire", () -> new BlockItem(DEATH_GENERATOR_BLOCK3.get(), new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
	
	public static final RegistryObject<Item> TIER2_UPGRADE = ITEMS.register("upgrade_2", () -> new UpgradeItem2(new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
	public static final RegistryObject<Item> TIER3_UPGRADE = ITEMS.register("upgrade_3", () -> new UpgradeItem3(new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE)));
	
	//BLOCK ENTITIES
	public static final RegistryObject<BlockEntityType<DeathGeneratorBlockEntity>> DEATH_GENERATOR_TYPE = BLOCKENTITIES.register("pumpkin_of_pain", () -> BlockEntityType.Builder.of(DeathGeneratorBlockEntity::new, DEATH_GENERATOR_BLOCK.get()).build(null));
	public static final RegistryObject<BlockEntityType<DeathGeneratorBlockEntity2>> DEATH_GENERATOR_TYPE2 = BLOCKENTITIES.register("pumpkin_of_suffering", () -> BlockEntityType.Builder.of(DeathGeneratorBlockEntity2::new, DEATH_GENERATOR_BLOCK2.get()).build(null));
	public static final RegistryObject<BlockEntityType<DeathGeneratorBlockEntity3>> DEATH_GENERATOR_TYPE3 = BLOCKENTITIES.register("pumpkin_of_hellfire", () -> BlockEntityType.Builder.of(DeathGeneratorBlockEntity3::new, DEATH_GENERATOR_BLOCK3.get()).build(null));
	
	//CONTAINERS
	/*public static final RegistryObject<MenuType<DeathGeneratorContainer>> GENERATOR_CONTAINER = CONTAINERS.register("death_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
		BlockPos pos = data.readBlockPos();
		Level world = inv.player.getCommandSenderWorld();
		return new DeathGeneratorContainer(windowId, world, pos, inv);
	}));*/
	
	//TAGS
	public static final Tags.IOptionalNamedTag<EntityType<?>> ANIMALS = EntityTypeTags.createOptional(new ResourceLocation(DeathGenerator.MOD_ID, "animals"));
	public static final Tags.IOptionalNamedTag<EntityType<?>> MONSTERS = EntityTypeTags.createOptional(new ResourceLocation(DeathGenerator.MOD_ID, "monsters"));
	public static final Tags.IOptionalNamedTag<EntityType<?>> ILLAGERS = EntityTypeTags.createOptional(new ResourceLocation(DeathGenerator.MOD_ID, "illagers"));
	public static final Tags.IOptionalNamedTag<EntityType<?>> VILLAGERS = EntityTypeTags.createOptional(new ResourceLocation(DeathGenerator.MOD_ID, "villagers"));
}
