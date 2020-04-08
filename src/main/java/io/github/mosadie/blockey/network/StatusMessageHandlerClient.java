package io.github.mosadie.blockey.network;

import io.github.mosadie.blockey.BlocKey;
import io.github.mosadie.blockey.common.KeyStatus;
import io.github.mosadie.blockey.client.BlocKeyClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class StatusMessageHandlerClient implements IMessageHandler<StatusMessage, StatusMessage> {
    @Override
    public StatusMessage onMessage(StatusMessage message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            StatusMessage reply = new StatusMessage();
            BlocKey blocKey = (BlocKey) FMLCommonHandler.instance().findContainerFor(BlocKey.MODID).getMod();
            BlocKeyClient client = blocKey.getBlocKeyClient();
            blocKey.getLogger().debug("Created Status Message object");
            for (String key : message.getKeys()) {
                String[] split = key.split(":");
                if (client.hasKey(split[0], split[1])) {
                    boolean enabled = client.getIfKeyEnabled(split[0], split[1]);
                    reply.addKeyStatus(key, (enabled ? KeyStatus.ENABLED : KeyStatus.DISABLED));
                    blocKey.getLogger().debug("Added key " + key + " with status " + enabled);
                } else {
                    reply.addKeyStatus(key, KeyStatus.ERROR);
                    blocKey.getLogger().debug("Couldn't find key " + key);
                }
            }
            BlocKeyPacketHandler.INSTANCE.sendToServer(reply);
        });
        return null;
    }
}
