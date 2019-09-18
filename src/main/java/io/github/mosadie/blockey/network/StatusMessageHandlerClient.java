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
            BlocKeyClient client = ((BlocKey) FMLCommonHandler.instance().findContainerFor(BlocKey.MODID).getMod()).getBlocKeyClient();
            for (String key : message.getKeys()) {
                String[] split = key.split(":");
                if (client.hasKey(split[0], split[1])) {
                    boolean enabled = client.getIfKeyEnabled(split[0], split[1]);
                    reply.addKeyStatus(key, (enabled ? KeyStatus.ENABLED : KeyStatus.DISABLED));
                } else {
                    reply.addKeyStatus(key, KeyStatus.ERROR);
                }
            }
        });
        return null;
    }
}
