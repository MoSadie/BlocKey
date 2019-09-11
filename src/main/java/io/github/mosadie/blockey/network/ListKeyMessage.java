package io.github.mosadie.blockey.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;

public class ListKeyMessage implements IMessage {

    public ListKeyMessage() {
        keyList = "";
    };

    private String keyList;

    public void addKey(String key) {
        keyList += key.toLowerCase().trim() + "\n";
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        keyList = (String) buf.readCharSequence(buf.readableBytes(), StandardCharsets.UTF_8);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeCharSequence(keyList, StandardCharsets.UTF_8);
    }

    public String getKeyList() {
        return keyList;
    }
}
