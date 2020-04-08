package io.github.mosadie.blockey.network;

import io.github.mosadie.blockey.BlocKey;
import io.github.mosadie.blockey.server.BlocKeyServer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Scanner;

public class ListKeyMessageHandler implements IMessageHandler<ListKeyMessage, IMessage> {
    @Override
    public IMessage onMessage(ListKeyMessage message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().player;

        String keyList = message.getKeyList();
        Scanner scanner = new Scanner(keyList);
        player.getServerWorld().addScheduledTask(() -> {
            BlocKeyServer server = ((BlocKey) FMLCommonHandler.instance().findContainerFor(BlocKey.MODID).getMod()).getBlocKeyServer();
            while (scanner.hasNextLine()) {
                String fullKey = scanner.nextLine();

                if (fullKey.contains(":")) {
                    String[] split = fullKey.split(":");
                    String modId = split[0];
                    String key = split[1];
                    server.addPlayerKey(player, modId.toLowerCase(), key.toLowerCase());
                }
            }

            server.updateKeyStatus(player);
        });
        return null;
    }
}
