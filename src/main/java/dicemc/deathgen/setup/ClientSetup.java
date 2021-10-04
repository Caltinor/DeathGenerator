package dicemc.deathgen.setup;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {
	public static void init(final FMLClientSetupEvent event) {
		ItemBlockRenderTypes.setRenderLayer(Registration.DEATH_GENERATOR_BLOCK.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(Registration.DEATH_GENERATOR_BLOCK2.get(), RenderType.translucent());
		ItemBlockRenderTypes.setRenderLayer(Registration.DEATH_GENERATOR_BLOCK3.get(), RenderType.translucent());
		
		ItemBlockRenderTypes.setRenderLayer(Registration.DEATH_GENERATOR_BLOCK.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Registration.DEATH_GENERATOR_BLOCK2.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(Registration.DEATH_GENERATOR_BLOCK3.get(), RenderType.cutout());
	}
}
