package io.github.mosadie.blockey.client.event;

import io.github.mosadie.blockey.api.IBlockableKey;
import io.github.mosadie.blockey.client.BlocKeyClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.common.eventhandler.Event;

public class BlocKeyRegisterEvent extends Event {
    private BlocKeyClient blocKeyClient;

    public BlocKeyRegisterEvent(BlocKeyClient blocKeyClient) {
        this.blocKeyClient = blocKeyClient;
    }

    @Override
    public boolean isCancelable() {
        return false;
    }

    public boolean registerKey(String modId, String keyName, IBlockableKey keyHandler) {
        return blocKeyClient.registerKey(modId, keyName, keyHandler);
    }

    public KeyBinding getOverrideKey() {
        return blocKeyClient.getOverrideKey();
    }

    public BlocKeyClient getBlocKeyClient() {
        return blocKeyClient;
    }
}
