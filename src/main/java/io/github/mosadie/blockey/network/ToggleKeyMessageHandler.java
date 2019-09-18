package io.github.mosadie.blockey.network;

import io.github.mosadie.blockey.BlocKey;
import io.github.mosadie.blockey.client.BlocKeyClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ToggleKeyMessageHandler implements IMessageHandler<ToggleKeyMessage, IMessage> {

    @Override
    public IMessage onMessage(ToggleKeyMessage message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            BlocKeyClient client = ((BlocKey) FMLCommonHandler.instance().findContainerFor(BlocKey.MODID).getMod()).getBlocKeyClient();
            client.toggleKey(message.getModId(), message.getKey(), message.getEnableKey());
        });
        return null;
    }
}
