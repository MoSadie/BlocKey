package io.github.mosadie.blockey.network;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ToggleKeyMessage implements IMessage {

	public ToggleKeyMessage() {
	}
	
	public ToggleKeyMessage(String modid, String key, boolean enableKey) {
		this.modid = modid;
		this.key = key;
		this.enableKey = enableKey;
	}
	
	private String modid;
	private String key;
	private boolean enableKey;
	
	@Override
	public void fromBytes(ByteBuf buf) {
		int modidLength = buf.readInt();
		modid = (String) buf.readCharSequence(modidLength, StandardCharsets.UTF_8);
		int keyLength = buf.readInt();
		key = (String) buf.readCharSequence(keyLength, StandardCharsets.UTF_8);
		enableKey = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(modid.getBytes(StandardCharsets.UTF_8).length);
		buf.writeCharSequence(modid, StandardCharsets.UTF_8);
		
		buf.writeInt(key.getBytes(StandardCharsets.UTF_8).length);
		buf.writeCharSequence(key, StandardCharsets.UTF_8);
		
		buf.writeBoolean(enableKey);
	}

}
