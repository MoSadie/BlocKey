package io.github.mosadie.blockey;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BKEventHandler {
	
	private final BlocKey sd;
	
	public BKEventHandler(BlocKey sd) {
		this.sd = sd;
	}
	
	@SubscribeEvent
	public void onClientSendMessage(ClientChatEvent event) {
		switch(event.getMessage().toLowerCase()) {
			case ".sdenable":
				sd.enable();
				sd.sendStatusMessage();
				event.setCanceled(true);
				break;
				
			case ".sddisable":
				sd.disable(null);
				sd.sendStatusMessage();
				event.setCanceled(true);
				break;
				
			case ".sdstatus":
				sd.sendStatusMessage();
				event.setCanceled(true);
				break;
		}
	}
}
