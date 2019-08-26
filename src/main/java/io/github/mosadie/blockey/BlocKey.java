package io.github.mosadie.blockey;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModDisabledEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.Map;

import org.apache.logging.log4j.Logger;

import io.github.mosadie.blockey.api.IBlockableKey;
import io.github.mosadie.blockey.server.CommandBlocKey;

@Mod(modid = BlocKey.MODID, name = BlocKey.NAME, version = BlocKey.VERSION, updateJSON = "https://github.com/MoSadie/BlocKey/raw/master/updateJSON.json")
public class BlocKey
{
    public static final String MODID = "blockkey";
    public static final String NAME = "BlocKey";
    public static final String VERSION = "1.0.0";
    
    @SidedProxy(modId = BlocKey.MODID, clientSide = "io.github.mosadie.blockey.ClientProxy", serverSide = "io.github.mosadie.blockey.ServerProxy")
	public static IProxy proxy;
    
    static Logger logger;
    private BKEventHandler eventHandler;
    private Map<String, Map<String, IBlockableKey>> registeredMods;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	// Get our logger
        logger = event.getModLog();
        
        // Setup event handler
        eventHandler = new BKEventHandler(this);
        MinecraftForge.EVENT_BUS.register(eventHandler);
        
        // Create override keybind
        overrideKeyBind = new KeyBinding("blockey.key.override", 999, "blockey.key.category");
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	// Grab the original key binding
    	origKeyBind = Minecraft.getMinecraft().gameSettings.keyBindDrop;
    	
    	// Enable drop key canceling
    	enable();
    	
    	ClientRegistry.registerKeyBinding(newKeyBind);
    	
    	logger.info("Registered alternate key binding.");
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
    	event.registerServerCommand(new CommandBlocKey(this));
    }
}
