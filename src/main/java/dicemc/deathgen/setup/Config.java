package dicemc.deathgen.setup;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
	public static ForgeConfigSpec SERVER_CONFIG;
	
	public static ForgeConfigSpec.ConfigValue<Double> DAMAGE_RATIO;
	public static ForgeConfigSpec.ConfigValue<Integer> DAMAGE_RANGE;
	public static ForgeConfigSpec.ConfigValue<Integer> GENERATOR_CAPACITY;
	public static ForgeConfigSpec.ConfigValue<Integer> GENERATOR_TRANSFER_RATE;
	public static ForgeConfigSpec.ConfigValue<Double> ENERGY_PER_HITPOINT;
	public static ForgeConfigSpec.ConfigValue<Boolean> KILL_ANIMALS;
	public static ForgeConfigSpec.ConfigValue<Boolean> KILL_MONSTERS;
	public static ForgeConfigSpec.ConfigValue<Boolean> KILL_ILLAGERS;
	public static ForgeConfigSpec.ConfigValue<Boolean> KILL_VILLAGERS;
	public static ForgeConfigSpec.ConfigValue<Boolean> KILL_PLAYERS;
	
	static {
		ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
		
		SERVER_BUILDER.comment("Server Settings").push("Generator Settings");
		setupServer(SERVER_BUILDER);
		SERVER_BUILDER.pop();
		
		SERVER_CONFIG = SERVER_BUILDER.build();
	}
	
	private static void setupServer(ForgeConfigSpec.Builder builder) {
		DAMAGE_RATIO = builder.comment("what percentage of damage should be drained per second")
				.defineInRange("Damage Ratio", 0.1, 0d, 1d);
		DAMAGE_RANGE = builder.comment("How far the generator should reach when draining entities")
				.defineInRange("Damage Range", 10, 1, Integer.MAX_VALUE);
		GENERATOR_CAPACITY = builder.comment("how much FE the base generator can hold")
				.defineInRange("Energy Capacity", 50000, 0, Integer.MAX_VALUE);
		GENERATOR_TRANSFER_RATE = builder.comment("how much FE the base generator can transfer in one operation")
				.defineInRange("Energy Capacity", 100, 0, Integer.MAX_VALUE);
		ENERGY_PER_HITPOINT = builder.comment("how much FE is generate per point of health drained")
				.defineInRange("Energy Per Hitpoint", 10, 0, Double.MAX_VALUE);
		KILL_ANIMALS = builder.comment("Does the generator kill animals")
				.define("Kill Animals", false);
		KILL_MONSTERS = builder.comment("Does the generator kill monsters")
				.define("Kill Monsters", true);
		KILL_ILLAGERS = builder.comment("Does the generator kill illagers")
				.define("Kill Illagers", true);
		KILL_VILLAGERS = builder.comment("Does the generator kill villagers")
				.define("Kill Villagers", false);
		KILL_PLAYERS = builder.comment("Does the generator kill players")
				.define("Kill Playerss", false);
	}
}
