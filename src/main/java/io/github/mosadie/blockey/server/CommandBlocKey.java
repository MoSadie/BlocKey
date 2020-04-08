package io.github.mosadie.blockey.server;

import java.util.Arrays;
import java.util.List;

import io.github.mosadie.blockey.BlocKey;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.server.permission.PermissionAPI;

public class CommandBlocKey extends CommandBase {
	
	private BlocKeyServer blocKeyServer;
	
	public CommandBlocKey(BlocKeyServer blocKeyServer) {
		super();
		this.blocKeyServer = blocKeyServer;
	}
	
	/**
	* Return the name of the command.
	*/
	@Override
	public String getName() {
		return "blockey";
	}
	
	/**
	* Get a list of aliases for this command. <b>Never return null!</b>
	*/
	@Override
	public List<String> getAliases()
	{
		return Arrays.<String>asList("bk");
	}
	
	/**
	* Return the required permission level for this command.
	*/
	public int getRequiredPermissionLevel()
	{
		return 0;
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		if (isAdvanced(sender)) {
			return "command.blockey.advanced.usage";
		}
		
		return "command.blockey.basic.usage";
	}
	
	private boolean isAdvanced(ICommandSender sender) {
		if (sender.equals(sender.getServer())) {
			return true;
		}
		try {
			EntityPlayerMP player = getCommandSenderAsPlayer(sender);
			if (PermissionAPI.hasPermission(player, BlocKey.MODID + ".command.blockey.advanced")) {
				return true;
			} else if (PermissionAPI.hasPermission(player, BlocKey.MODID + ".command.blockey.basic")) {
				return false;
			}
			
			return false;
		} catch (PlayerNotFoundException e) {
			// Do nothing, assume basic perms
		}
		return false;
	}
	
	/*
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1) {
			throw new WrongUsageException(getUsage(sender));
		}
		
		boolean advanced = false;
		if (sender.equals(server)) {
			advanced = true;
		} else {
			try {
				advanced = (sender instanceof MinecraftServer) || PermissionAPI.hasPermission(getCommandSenderAsPlayer(sender), BlocKey.MODID + ".command.blockey.advanced");
			} catch (PlayerNotFoundException e) {
				// Player not found, assuming basic perms.
			}
		}
		
		if (!advanced) {
			if (args.length > 2) {
				throw new WrongUsageException("command.blockey.basic.usage");
			}
			String modId;
			String key;
			EntityPlayerMP player;
			switch(args[0].toLowerCase()) {
				case "enable":
				if (args.length < 2) {
					throw new WrongUsageException("command.blockey.basic.usage");
				}
				
				modId = "minecraft";
				key = args[1];
				
				if (args[1].contains(":")) {
					String[] split = args[1].split(":");
					if (split.length > 1) {
						modId = split[0];
						key = "";
						for (int i = 1; i < split.length; i++) {
							key += split[i];
						}
					}
				}
				
				modId = modId.toLowerCase();
				key = key.toLowerCase();
				
				try {
					player = getCommandSenderAsPlayer(sender);
				} catch (PlayerNotFoundException e) {
					notifyCommandListener(sender, this, "command.blockey.error.playernotfound");
					return;
				}
				
				if (!blocKeyServer.hasMod(player, modId)) {
					notifyCommandListener(sender, this, "command.blockey.error.modnotfound", modId);
					return;
				}
				
				if (!blocKeyServer.hasKey(player, modId, key)) {
					notifyCommandListener(sender, this, "command.blockey.error.keynotfound", key, modId);
					return;
				}
				
				blocKeyServer.enableKey(player, modId, key);
				notifyCommandListener(sender, this, "command.blockey.success", player.getName(), key, modId);
				return;
				
				case "disable":
				if (args.length < 2) {
					throw new WrongUsageException("command.blockey.basic.usage");
				}
				
				modId = "minecraft";
				key = args[1];
				
				if (args[1].contains(":")) {
					String[] split = args[1].split(":");
					if (split.length > 1) {
						modId = split[0];
						key = "";
						for (int i = 1; i < split.length; i++) {
							key += split[i];
						}
					}
				}
				
				modId = modId.toLowerCase();
				key = key.toLowerCase();
				
				try {
					player = getCommandSenderAsPlayer(sender);
				} catch (PlayerNotFoundException e) {
					notifyCommandListener(sender, this, "command.blockey.error.generic");
					return;
				}
				
				if (!blocKeyServer.hasMod(player, modId)) {
					notifyCommandListener(sender, this, "command.blockey.error.modnotfound", modId);
					return;
				}
				
				if (!blocKeyServer.hasKey(player, modId, key)) {
					notifyCommandListener(sender, this, "command.blockey.error.keynotfound", key, modId);
					return;
				}
				
				blocKeyServer.disableKey(player, modId, key);
				notifyCommandListener(sender, this, "command.blockey.success", player.getName(), key, modId);
				return;
				
				case "status":
				
			}
		} else {
			if (args.length > 3) {
				throw new WrongUsageException("command.blockey.basic.usage");
			}
			String modId;
			String key;
			EntityPlayerMP player;
			switch(args[0].toLowerCase()) {
				case "enable":
				if (args.length < 2 || args.length > 3) {
					throw new WrongUsageException("command.blockey.basic.usage");
				}
				
				modId = "minecraft";
				key = args[1];
				
				if (args[1].contains(":")) {
					String[] split = args[1].split(":");
					if (split.length > 1) {
						modId = split[0];
						key = "";
						for (int i = 1; i < split.length; i++) {
							key += split[i];
						}
					}
				}
				
				modId = modId.toLowerCase();
				key = key.toLowerCase();
				
				try {
					if (args.length == 2)
					player = getCommandSenderAsPlayer(sender);
					else {
						player = FMLServerHandler.instance().getServer().getPlayerList().getPlayerByUsername(args[2]);
					}
				} catch (PlayerNotFoundException e) {
					notifyCommandListener(sender, this, "command.blockey.error.playernotfound");
					return;
				}
				
				if (!blocKeyServer.hasMod(player, modId)) {
					notifyCommandListener(sender, this, "command.blockey.error.modnotfound", modId);
					return;
				}
				
				if (!blocKeyServer.hasKey(player, modId, key)) {
					notifyCommandListener(sender, this, "command.blockey.error.keynotfound", key, modId);
					return;
				}
				
				blocKeyServer.enableKey(player, modId, key);
				notifyCommandListener(sender, this, "command.blockey.success", player.getName(), key, modId);
				return;
				
				case "disable":
				if (args.length < 2 || args.length > 3) {
					throw new WrongUsageException("command.blockey.basic.usage");
				}
				
				modId = "minecraft";
				key = args[1];
				
				if (args[1].contains(":")) {
					String[] split = args[1].split(":");
					if (split.length > 1) {
						modId = split[0];
						key = "";
						for (int i = 1; i < split.length; i++) {
							key += split[i];
						}
					}
				}
				
				modId = modId.toLowerCase();
				key = key.toLowerCase();
				
				try {
					if (args.length == 2)
					player = getCommandSenderAsPlayer(sender);
					else {
						player = FMLServerHandler.instance().getServer().getPlayerList().getPlayerByUsername(args[2]);
					}
				} catch (PlayerNotFoundException e) {
					notifyCommandListener(sender, this, "command.blockey.error.playernotfound");
					return;
				}
				
				if (player == null) {
					notifyCommandListener(sender, this, "command.blockey.error.playernotfound");
					return;
				}
				
				if (!blocKeyServer.hasMod(player, modId)) {
					notifyCommandListener(sender, this, "command.blockey.error.modnotfound", modId);
					return;
				}
				
				if (!blocKeyServer.hasKey(player, modId, key)) {
					notifyCommandListener(sender, this, "command.blockey.error.keynotfound", key, modId);
					return;
				}
				
				blocKeyServer.disableKey(player, modId, key);
				notifyCommandListener(sender, this, "command.blockey.success", player.getName(), key, modId);
				return;
			}
		}
	}
	*/
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length > 3 || args.length < 2) {
			throw new WrongUsageException(getUsage(sender));
		}
		
		EntityPlayerMP player;
		
		if (isAdvanced(sender) && args.length == 3) {
			player = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUsername(args[2]);
			
			if (player == null) {
				notifyCommandListener(sender, this, "command.blockey.advanced.playernotfound", args[2]);
				return;
			}
		} else {
			try {
				player = getCommandSenderAsPlayer(sender);
			} catch (PlayerNotFoundException e) {
				notifyCommandListener(sender, this, "command.blockey.error.generic");
				return;
			}
		}
		
		String modId;
		String key;
		
		modId = "minecraft";
		key = args[1];
		
		if (args[1].contains(":")) {
			String[] split = args[1].split(":");
			if (split.length > 1) {
				modId = split[0];
				key = "";
				for (int i = 1; i < split.length; i++) {
					key += split[i];
				}
			}
		}
		modId = modId.toLowerCase();
		key = key.toLowerCase();
		
		switch(args[0].toLowerCase()) {
			case "enable":
			if (args.length < 2 || args.length > 3) {
				throw new WrongUsageException("command.blockey.basic.usage");
			}

			if (!blocKeyServer.hasMod(player, modId)) {
				notifyCommandListener(sender, this, "command.blockey.error.modnotfound", modId, player.getName());
				return;
			}
			
			if (!blocKeyServer.hasKey(player, modId, key)) {
				notifyCommandListener(sender, this, "command.blockey.error.keynotfound", key, modId, player.getName());
				return;
			}
			
			blocKeyServer.enableKey(player, modId, key);
			notifyCommandListener(sender, this, "command.blockey.success", player.getName(), key, modId);
			return;
			
			case "disable":
			if (args.length < 2 || args.length > 3) {
				throw new WrongUsageException("command.blockey.basic.usage");
			}
		
			if (!blocKeyServer.hasMod(player, modId)) {
				notifyCommandListener(sender, this, "command.blockey.error.modnotfound", modId, player.getName());
				return;
			}
			
			if (!blocKeyServer.hasKey(player, modId, key)) {
				notifyCommandListener(sender, this, "command.blockey.error.keynotfound", key, modId, player.getName());
				return;
			}
			
			blocKeyServer.disableKey(player, modId, key);
			notifyCommandListener(sender, this, "command.blockey.success", player.getName(), key, modId, "disabled");
			return;
			
			case "status":
			if (blocKeyServer.hasKey(player, modId, key)) {
				String status;
				switch (blocKeyServer.getKeyStatus(player, modId, key)) {
					case ENABLED:
						status = "enabled";
						break;

					case DISABLED:
						status = "disabled";
						break;

					default:
						status = "unknown";
						break;

				}
				notifyCommandListener(sender, this, "command.blockey.status", player.getName(), (modId == "minecraft" ? key : modId + ":" + key), status);
			} else {
				if (!blocKeyServer.hasPlayer(player)) {
					notifyCommandListener(sender, this, "command.blockey.error.playernotfound", player.getName());
				}
			}
			return;
		}
		
	}
}
