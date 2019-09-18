package io.github.mosadie.blockey.network;

import io.github.mosadie.blockey.common.KeyStatus;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

public class StatusMessage implements IMessage {

    Map<String, KeyStatus> statusMap;

    @Override
    public void fromBytes(ByteBuf buf) {
        String data = (String) buf.readCharSequence(buf.readableBytes(), StandardCharsets.UTF_8);
        Scanner scanner = new Scanner(data);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(":");
            if (split.length == 3) {
                statusMap.put(split[0] + ":" + split[1], KeyStatus.stringToStatus(split[2]));
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (String key : statusMap.keySet()) {
            buf.writeCharSequence(key + ":" + statusMap.get(key).toString() + "\n", StandardCharsets.UTF_8);
        }
    }

    public void addKeyStatus(String key, KeyStatus status) {
        statusMap.put(key, status);
    }

    public KeyStatus getKeyStatus(String key) {
        if (statusMap.containsKey(key))
            return statusMap.get(key);
        else
            return KeyStatus.ERROR;
    }

    public String[] getKeys() {
        return (String[]) statusMap.keySet().toArray();
    }
}
