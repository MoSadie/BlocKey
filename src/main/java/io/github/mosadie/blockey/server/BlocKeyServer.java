package io.github.mosadie.blockey.server;

import com.mojang.authlib.GameProfile;
import io.github.mosadie.blockey.BlocKey;
import io.github.mosadie.blockey.network.BlocKeyPacketHandler;
import io.github.mosadie.blockey.network.RegisterKeyMessage;
import io.github.mosadie.blockey.network.ToggleKeyMessage;
import net.minecraft.entity.player.EntityPlayerMP;
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
        BlocKeyPacketHandler.registerMessages();
        blocKey.getLogger().info("Created BlocKeyServer");

        BKServerEventHandler eventHandler = new BKServerEventHandler(this);
        MinecraftForge.EVENT_BUS.register(eventHandler);
    }

    void refreshPlayerMap() {
        blocKey.getLogger().info("Refreshing Player Map");
        players = new HashMap<>();
        for(GameProfile profile : FMLServerHandler.instance().getServer().getOnlinePlayerProfiles()) {
            EntityPlayerMP player = FMLServerHandler.instance().getServer().getPlayerList().getPlayerByUUID(profile.getId());
            if (player == null) {
                continue;
            }
            blocKey.getLogger().debug("Sending Register Packet to " + player.getName());
            BlocKeyPacketHandler.INSTANCE.sendTo(new RegisterKeyMessage(), player);
        }
    }

    public void addPlayerKey(EntityPlayerMP player, String modId, String key) {
        if (!players.containsKey(player)) {
            players.put(player, new HashMap<>());
            blocKey.getLogger().info("Added Player " + player.getName() + " to Player Map");
        }

        if (!players.get(player).containsKey(modId)) {
            players.get(player).put(modId, new ArrayList<>());
            blocKey.getLogger().info("Added Mod " + modId + " to Player " + player.getName() + "'s Map");
        }

        if (!players.get(player).get(modId).contains(key)){
            players.get(player).get(modId).add(key);
            blocKey.getLogger().info("Added Key " + key + " to Player " + player.getName() + "'s " + modId + " Map");
        }
    }

    public boolean hasMod(EntityPlayerMP player, String modId) {
        return players.containsKey(player) && players.get(player).containsKey(modId.toLowerCase());
    }

    public boolean hasKey(EntityPlayerMP player, String modId, String key) {
        return hasMod(player, modId) && players.get(player).get(modId.toLowerCase()).contains(key.toLowerCase());
    }

    public void enableKey(EntityPlayerMP player, String modId, String key) {
        if (hasKey(player, modId, key)) {
            BlocKeyPacketHandler.INSTANCE.sendTo(new ToggleKeyMessage(modId, key, true), player);
            blocKey.getLogger().info("Sent enable packet to " + player.getName() + " (Mod: " + modId + " Key: " + key + ")");
        }
    }

    public void disableKey(EntityPlayerMP player, String modId, String key) {
        if (hasKey(player, modId, key)) {
            BlocKeyPacketHandler.INSTANCE.sendTo(new ToggleKeyMessage(modId, key, false), player);
            blocKey.getLogger().info("Sent disable packet to " + player.getName() + " (Mod: " + modId + " Key: " + key + ")");
        }
    }
}
