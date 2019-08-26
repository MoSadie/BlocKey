package io.github.mosadie.blockey.server;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.mosadie.blockey.BlocKey;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.permission.PermissionAPI;

public class CommandBlocKey extends CommandBase {
	
	private BlocKey blocKey;
	
	public CommandBlocKey(BlocKey blocKey) {
		super();
		this.blocKey = blocKey;
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
		if (sender.equals(sender.getServer())) {
			return "command.blockey.advanced.usage";
		}
		try {
			EntityPlayerMP player = getCommandSenderAsPlayer(sender);
			if (PermissionAPI.hasPermission(player, BlocKey.MODID + ".command.blockey.advanced")) {
				return "command.blockey.advanced.usage";
			} else if (PermissionAPI.hasPermission(player, BlocKey.MODID + ".command.blockey.basic")) {
				return "command.blockey.basic.usage";
			}
			
			return "command.blockey.basic.usage";
		} catch (PlayerNotFoundException e) {
			// Do nothing, assume basic perms
		}
		return "command.blockey.basic.usage";
	}

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
				advanced = PermissionAPI.hasPermission(getCommandSenderAsPlayer(sender), BlocKey.MODID + ".command.blockey.advanced");
			} catch (PlayerNotFoundException e) {
				// Player not found, assuming basic perms.
			}
		}
		
		if (!advanced) {
			if (args.length > 2) {
				throw new WrongUsageException("command.blockey.basic.usage");
			}
			switch(args[0].toLowerCase()) {
			case "enable":
				if (args.length < 2) {
					throw new WrongUsageException("command.blockey.basic.usage");
				}
				
				Map<String, Set<String>> map = BlocKey.proxy.getKeys();
				
				String modid = "minecraft";
				String key = args[1];
				
				if (args[1].contains(":")) {
					String[] split = args[1].split(":");
					if (split.length > 1) {
						modid = split[0];
						key = "";
						for (int i = 1; i < split.length; i++) {
							key += split[i];
						}
					}
				}
				
				if (!map.containsKey(modid)) {
					notifyCommandListener(sender, this, "command.blockey.error.modnotfound", modid);
					return;
				}
				
				if (!map.get(modid).contains(key)) {
					notifyCommandListener(sender, this, "command.blockey.error.keynotfound", key, modid);
					return;
				}
				
				try {
				EntityPlayerMP player = getCommandSenderAsPlayer(sender);
				}
				break;
			}
		}
	}

}
