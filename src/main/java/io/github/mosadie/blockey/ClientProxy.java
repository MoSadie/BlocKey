package io.github.mosadie.blockey;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import io.github.mosadie.blockey.api.IBlockableKey;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

class ClientProxy implements IProxy {

    static Logger logger;
    private BKClientEventHandler handler;
    private Map<String, Map<String, IBlockableKey>> registeredMods;

    private boolean onlineMode = false;

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

	@Override
	public Map<String, Set<String>> getKeys() {
		Map<String, Set<String>> map = new TreeMap<>();
		for (String key : registeredMods.keySet()) {
			map.put(key, new HashSet<String>());
			map.get(key).addAll(registeredMods.get(key).keySet());
		}
		
		return map;
	}
    
}