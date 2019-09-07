package io.github.mosadie.blockey.network;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ToggleKeyMessageHandler implements IMessageHandler<ToggleKeyMessage, IMessage> {

    @Override
    public IMessage onMessage(ToggleKeyMessage message, MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {

        });
        return null;
    }
}
