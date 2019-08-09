package io.github.mosadie.stopdrop;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SDEventHandler {
	
	private final StopDrop sd;
	
	public SDEventHandler(StopDrop sd) {
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
