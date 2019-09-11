package io.github.mosadie.blockey.server;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BKServerEventHandler {

    private BlocKeyServer server;

    public BKServerEventHandler(BlocKeyServer server) {
        this.server = server;
    }

    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityPlayerMP) {
            server.refreshPlayerMap();
        }
    }

    //TODO Improvement: Detect when players leave the server. Removes the need to regenerate the whole list when one player logs in.
}
