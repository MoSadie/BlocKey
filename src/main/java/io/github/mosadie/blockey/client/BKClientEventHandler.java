package io.github.mosadie.blockey.client;

import io.github.mosadie.blockey.BlocKey;
import io.github.mosadie.blockey.common.event.BlocKeyRegisterEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BKClientEventHandler {
	
	private final BlocKeyClient bkc;
	private final VanillaKeyExample vanillaKeyExample;
	
	public BKClientEventHandler(BlocKeyClient bkc) {
		this.bkc = bkc;
		vanillaKeyExample = new VanillaKeyExample(bkc);
	}
	
	@SubscribeEvent
	public void onRegisterEvent(BlocKeyRegisterEvent event) {
		for (String key : vanillaKeyExample.getKeys()) {
			event.registerKey("minecraft", key, vanillaKeyExample);
		}
	}
}
