package io.github.mosadie.blockey;

import java.util.Map;
import java.util.Set;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;

public class ServerProxy implements IProxy {
	
	public Map<String, Set<String>> registeredKeybindings;

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FMLInitializationEvent event) {
		
		// Setup Permissions
		PermissionAPI.registerNode(BlocKey.MODID + ".command.blockey.basic", DefaultPermissionLevel.ALL, "Allows access to control your own keys.");
		PermissionAPI.registerNode(BlocKey.MODID + ".commands.blockey.advanced", DefaultPermissionLevel.OP, "Allows access to control other player's keys.");
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Set<String>> getKeys() {
		return registeredKeybindings;
	}

}
