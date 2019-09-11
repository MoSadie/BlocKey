package io.github.mosadie.blockey.network;

import io.github.mosadie.blockey.BlocKey;
import io.github.mosadie.blockey.client.BlocKeyClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.List;

public class RegisterKeyMessageHandler implements IMessageHandler<RegisterKeyMessage, ListKeyMessage> {

    public RegisterKeyMessageHandler() { }

    @Override
    public ListKeyMessage onMessage(RegisterKeyMessage message, MessageContext ctx) {
        ListKeyMessage reply = new ListKeyMessage();
        Minecraft.getMinecraft().addScheduledTask(() -> {
            List<String> keys = ((BlocKey) FMLCommonHandler.instance().findContainerFor(BlocKey.MODID).getMod()).getBlocKeyClient().exportRegisteredKeys();

            for(String key : keys) {
                reply.addKey(key);
            }
        });

        return reply;
    }
}
