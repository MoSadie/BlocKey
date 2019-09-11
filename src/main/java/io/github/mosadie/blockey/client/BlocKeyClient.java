package io.github.mosadie.blockey.client;

import io.github.mosadie.blockey.BlocKey;
import io.github.mosadie.blockey.api.IBlockableKey;
import io.github.mosadie.blockey.client.event.BlocKeyRegisterEvent;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BlocKeyClient {

    private BlocKey blocKey;
    private Logger logger;
    private BKClientEventHandler eventHandler;

    private Map<String, Map<String, IBlockableKey>> registeredMods;

    //TODO See if custom KeyBinding class is better than key 999.
    private KeyBinding overrideKey = new KeyBinding("blockey.key.override", 999, "blockey.key.category");

    public BlocKeyClient(BlocKey blocKey, Logger logger) {
        this.blocKey = blocKey;
        this.logger = logger;
        registeredMods = new TreeMap<>();
        ClientCommandHandler.instance.registerCommand(new CommandBlocKeyClient(this));

        eventHandler = new BKClientEventHandler();
        MinecraftForge.EVENT_BUS.register(eventHandler);

        MinecraftForge.EVENT_BUS.post(new BlocKeyRegisterEvent(this));
    }

    public void registerOverrideKey() {
        ClientRegistry.registerKeyBinding(overrideKey);
    }

    public KeyBinding getOverrideKey() {
        return overrideKey;
    }

    public boolean registerKey(String modId, String keyName, IBlockableKey keyHandler) {
        modId = modId.toLowerCase().trim();
        keyName = keyName.toLowerCase().trim();
        if (!registeredMods.containsKey(modId)) {
            registeredMods.put(modId, new TreeMap<>());
        }

        if (!registeredMods.get(modId).containsKey(keyName)) {
            registeredMods.get(modId).put(keyName, keyHandler);
            logger.info("Registered key " + keyName + " from modId " + modId);
            return true;
        }

        return false;
    }

    public List<String> exportRegisteredKeys() {
        List<String> list = new ArrayList<>();
        for(String modId : registeredMods.keySet()) {
            for(String key : registeredMods.get(modId).keySet()) {
                list.add(modId + ":" + key);
            }
        }

        return list;
    }

    public boolean enableKey(String modId, String key) {
        return toggleKey(modId, key, true);
    }

    public boolean disableKey(String modId, String key) {
        return toggleKey(modId, key, false);
    }

    public boolean toggleKey(String modId, String key, boolean enableKey) {
        modId = modId.toLowerCase().trim();
        key = key.toLowerCase().trim();
        if (!registeredMods.containsKey(modId) || !registeredMods.get(modId).containsKey(key)) {
            return false;
        }

        if (enableKey) {
            return registeredMods.get(modId).get(key).enableKeybinding(key);
        } else {
            return registeredMods.get(modId).get(key).disableKeybinding(key);
        }
    }
}
