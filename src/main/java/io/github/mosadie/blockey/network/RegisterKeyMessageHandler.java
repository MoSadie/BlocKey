package io.github.mosadie.blockey.network;

import io.github.mosadie.blockey.BlocKey;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;

public class RegisterKeyMessageHandler implements IMessageHandler<RegisterKeyMessage, IMessage> {

    public RegisterKeyMessageHandler() { }

    @Override
    public ListKeyMessage onMessage(RegisterKeyMessage message, MessageContext ctx) {
        BlocKey blocKey = ((BlocKey) FMLCommonHandler.instance().findContainerFor(BlocKey.MODID).getMod());
        blocKey.getLogger().info("Received Register Key Message.");
        Minecraft.getMinecraft().addScheduledTask(() -> {
            List<String> keys = blocKey.getBlocKeyClient().exportRegisteredKeys();
            ListKeyMessage reply = new ListKeyMessage();

            for(String key : keys) {
                reply.addKey(key);
                blocKey.getLogger().debug("Added key to reply: " + key);
            }

            blocKey.getLogger().info("Sending response packet");
            BlocKeyPacketHandler.INSTANCE.sendToServer(reply);
        });

        return null;
    }
}
