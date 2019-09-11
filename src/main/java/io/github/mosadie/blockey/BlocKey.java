package io.github.mosadie.blockey;

import io.github.mosadie.blockey.client.BKClientEventHandler;
import io.github.mosadie.blockey.client.BlocKeyClient;
import io.github.mosadie.blockey.server.BlocKeyServer;
import io.github.mosadie.blockey.server.CommandBlocKey;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.util.Map;

import net.minecraftforge.fml.relauncher.Side;
import org.apache.logging.log4j.Logger;

import io.github.mosadie.blockey.api.IBlockableKey;

@Mod(modid = BlocKey.MODID, name = BlocKey.NAME, version = BlocKey.VERSION, updateJSON = "https://github.com/MoSadie/BlocKey/raw/master/updateJSON.json")
public class BlocKey
{
    public static final String MODID = "blockey";
    public static final String NAME = "BlocKey";
    public static final String VERSION = "2.0.0";

    static Logger logger;
    private Map<String, Map<String, IBlockableKey>> registeredMods;

    private BlocKeyClient blocKeyClient;
    private BlocKeyServer blocKeyServer;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	// Get our logger
        logger = event.getModLog();

        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            blocKeyClient = new BlocKeyClient(this, logger);
        }
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

        if (blocKeyClient != null) {
            blocKeyClient.registerOverrideKey();

            logger.info("Registered alternate key binding.");
        }
    }

    @EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        logger.info("Creating BlocKey Sever object.");
    	blocKeyServer = new BlocKeyServer(this);

        logger.info("Registering BlocKey command.");
    	event.registerServerCommand(new CommandBlocKey(blocKeyServer));
    }

    public BlocKeyClient getBlocKeyClient() {
        return blocKeyClient;
    }

    public BlocKeyServer getBlocKeyServer() {
        return blocKeyServer;
    }
}
