package io.github.mosadie.blockey.server;

import com.mojang.authlib.GameProfile;
import io.github.mosadie.blockey.BlocKey;
import io.github.mosadie.blockey.network.BlocKeyPacketHandler;
import io.github.mosadie.blockey.network.RegisterKeyMessage;
import io.github.mosadie.blockey.network.ToggleKeyMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.server.FMLServerHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlocKeyServer {

    private Map<EntityPlayerMP, Map<String, List<String>>> players;
    private final BlocKey blocKey;

    public BlocKeyServer(BlocKey blocKey) {
        this.blocKey = blocKey;
        refreshPlayerMap();
    }

    void refreshPlayerMap() {
        players = new HashMap<>();
        for(GameProfile profile : FMLServerHandler.instance().getServer().getOnlinePlayerProfiles()) {
            EntityPlayerMP player = FMLServerHandler.instance().getServer().getPlayerList().getPlayerByUUID(profile.getId());
            if (player == null) {
                continue;
            }
            BlocKeyPacketHandler.INSTANCE.sendTo(new RegisterKeyMessage(), player);
        }
    }

    public void addPlayerKey(EntityPlayerMP player, String modId, String key) {
        if (!players.containsKey(player)) {
            players.put(player, new HashMap<>());
        }

        if (!players.get(player).containsKey(modId)) {
            players.get(player).put(modId, new ArrayList<>());
        }

        if (!players.get(player).get(modId).contains(key)){
            players.get(player).get(modId).add(key);
        }
    }

    public boolean hasMod(EntityPlayerMP player, String modId) {
        return players.containsKey(player) && players.get(player).containsKey(modId.toLowerCase());
    }

    public boolean hasKey(EntityPlayerMP player, String modId, String key) {
        return hasMod(player, modId) && players.get(player).get(modId.toLowerCase()).contains(key.toLowerCase());
    }

    public void enableKey(EntityPlayerMP player, String modId, String key) {
        if (hasKey(player, modId, key))
            BlocKeyPacketHandler.INSTANCE.sendTo(new ToggleKeyMessage(modId, key, true), player);
    }

    public void disableKey(EntityPlayerMP player, String modId, String key) {
        if (hasKey(player, modId, key))
            BlocKeyPacketHandler.INSTANCE.sendTo(new ToggleKeyMessage(modId, key, false), player);
    }
}
