package io.github.mosadie.blockey;

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
    
}