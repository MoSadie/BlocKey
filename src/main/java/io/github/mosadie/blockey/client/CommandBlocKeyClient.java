package io.github.mosadie.blockey.client;

import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.IClientCommand;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandBlocKeyClient implements IClientCommand {

    private BlocKeyClient client;

    public CommandBlocKeyClient(BlocKeyClient client) {
        this.client = client;
    }

    @Override
    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
        return false;
    }

    @Override
    public String getName() {
        return "blockeyclient";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.blockeyclient.usage";
    }

    private static final List<String> aliases = Arrays.asList(new String[] {"bkc"});

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args == null || args.length < 1 || args.length > 2) {
            throw new WrongUsageException(I18n.format(getUsage(sender)));
        }

        switch (args[0].toLowerCase()) {
            case "enable":
                handleEnableDisableCommand(sender, args, true);
                break;

            case "disable":
                handleEnableDisableCommand(sender, args, false);
                break;

            case "status":
                break;

            case "list":
                break;
        }
    }

    private void handleEnableDisableCommand(ICommandSender sender, String[] args, boolean enable) throws WrongUsageException {
        if (args.length != 2) {
            throw new WrongUsageException(I18n.format(getUsage(sender)));
        }

        String modId;
        String key;

        if (args[1].contains(":")) {
            String[] split = args[1].split(":");
            modId = split[0];
            key = split[1];
        } else {
            modId = "minecraft";
            key = args[1];
        }

        String suffix;
        boolean result;
        if (enable) {
            suffix = "enabled";
            result = client.enableKey(modId, key);
        } else {
            suffix = "disabled";
            result = client.disableKey(modId, key);
        }

        if (result) {
            sender.sendMessage(new TextComponentTranslation("command.blockeyclient.success", key, modId, suffix));
        } else {
            sender.sendMessage(new TextComponentTranslation("command.blockeyclient.failure", key, modId, suffix));
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand other) {
        return this.getName().compareTo(other.getName());
    }
}
