package dicemc.deathgen;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dicemc.deathgen.setup.ClientSetup;
import dicemc.deathgen.setup.Config;
import dicemc.deathgen.setup.Registration;

@Mod(DeathGenerator.MOD_ID)
public class DeathGenerator
{
    public static final String MOD_ID = "dicemcdeathgen";
    public static final Logger LOGGER = LogManager.getLogger();

    public DeathGenerator() {
    	ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
    	Registration.init();
    	FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
    }
}
