package io.github.mosadie.blockey.network;

import io.github.mosadie.blockey.BlocKey;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class BlocKeyPacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(BlocKey.MODID);

	private int id = 0;

	public void registerClientMessages() {
		INSTANCE.registerMessage(ToggleKeyMessageHandler.class, ToggleKeyMessage.class, id++, Side.CLIENT);
		INSTANCE.registerMessage(RegisterKeyMessageHandler.class, RegisterKeyMessage.class, id++, Side.CLIENT);
	}

	public void registerServerMessages() {
		INSTANCE.registerMessage(ListKeyMessageHandler.class, ListKeyMessage.class, id++, Side.SERVER);
	}
}
