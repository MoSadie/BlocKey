package io.github.mosadie.blockey.client;

import io.github.mosadie.blockey.client.event.BlocKeyRegisterEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BKClientEventHandler {

	private VanillaKeyExample vanillaKeyExample;
	
	@SubscribeEvent
	public void onRegisterEvent(BlocKeyRegisterEvent event) {
		vanillaKeyExample = new VanillaKeyExample(event.getBlocKeyClient());
		for (String key : vanillaKeyExample.getKeys()) {
			event.registerKey("minecraft", key, vanillaKeyExample);
		}
	}
}
