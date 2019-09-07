package io.github.mosadie.blockey.client;

import io.github.mosadie.blockey.api.IBlockableKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class VanillaKeyExample implements IBlockableKey {
    private Map<String, KeyBinding> vanillaKeys;

    private BlocKeyClient client;

    public VanillaKeyExample(BlocKeyClient client) {
        this.client = client;
        vanillaKeys = new HashMap<>();

        Minecraft minecraft = Minecraft.getMinecraft();
        vanillaKeys.put("forward", minecraft.gameSettings.keyBindForward);
        vanillaKeys.put("left", minecraft.gameSettings.keyBindLeft);
        vanillaKeys.put("back", minecraft.gameSettings.keyBindBack);
        vanillaKeys.put("right", minecraft.gameSettings.keyBindRight);
        vanillaKeys.put("jump", minecraft.gameSettings.keyBindJump);
        vanillaKeys.put("sneak", minecraft.gameSettings.keyBindSneak);
        vanillaKeys.put("sprint", minecraft.gameSettings.keyBindSprint);
        vanillaKeys.put("inventory", minecraft.gameSettings.keyBindInventory);
        vanillaKeys.put("swap_hands", minecraft.gameSettings.keyBindSwapHands);
        vanillaKeys.put("drop", minecraft.gameSettings.keyBindDrop);
        vanillaKeys.put("use", minecraft.gameSettings.keyBindUseItem);
        vanillaKeys.put("attack", minecraft.gameSettings.keyBindAttack);
        vanillaKeys.put("pick_block", minecraft.gameSettings.keyBindPickBlock);
        vanillaKeys.put("chat", minecraft.gameSettings.keyBindChat);
        vanillaKeys.put("player_list", minecraft.gameSettings.keyBindPlayerList);
        vanillaKeys.put("command", minecraft.gameSettings.keyBindCommand);
        vanillaKeys.put("screenshot", minecraft.gameSettings.keyBindScreenshot);
        vanillaKeys.put("toggle_perspective", minecraft.gameSettings.keyBindTogglePerspective);
        vanillaKeys.put("smooth_camera", minecraft.gameSettings.keyBindSmoothCamera);
        vanillaKeys.put("fullscreen", minecraft.gameSettings.keyBindFullscreen);
        vanillaKeys.put("spectator_outlines", minecraft.gameSettings.keyBindSpectatorOutlines);
        vanillaKeys.put("advancements", minecraft.gameSettings.keyBindAdvancements);
        vanillaKeys.put("hotbar_1", minecraft.gameSettings.keyBindsHotbar[0]);
        vanillaKeys.put("hotbar_2", minecraft.gameSettings.keyBindsHotbar[1]);
        vanillaKeys.put("hotbar_3", minecraft.gameSettings.keyBindsHotbar[2]);
        vanillaKeys.put("hotbar_4", minecraft.gameSettings.keyBindsHotbar[3]);
        vanillaKeys.put("hotbar_5", minecraft.gameSettings.keyBindsHotbar[4]);
        vanillaKeys.put("hotbar_6", minecraft.gameSettings.keyBindsHotbar[5]);
        vanillaKeys.put("hotbar_7", minecraft.gameSettings.keyBindsHotbar[6]);
        vanillaKeys.put("hotbar_8", minecraft.gameSettings.keyBindsHotbar[7]);
        vanillaKeys.put("hotbar_9", minecraft.gameSettings.keyBindsHotbar[8]);
        vanillaKeys.put("save_toolbar", minecraft.gameSettings.keyBindSaveToolbar);
        vanillaKeys.put("load_toolbar", minecraft.gameSettings.keyBindLoadToolbar);
    };

    @Override
    public boolean enableKeybinding(String keyId) {
        switch(keyId) {
            case "forward":
                Minecraft.getMinecraft().gameSettings.keyBindForward = vanillaKeys.get("forward");
                return true;
            case "left":
                Minecraft.getMinecraft().gameSettings.keyBindLeft = vanillaKeys.get("left");
                return true;
            case "back":
                Minecraft.getMinecraft().gameSettings.keyBindBack = vanillaKeys.get("back");
                return true;
            case "right":
                Minecraft.getMinecraft().gameSettings.keyBindRight = vanillaKeys.get("right");
                return true;
            case "jump":
                Minecraft.getMinecraft().gameSettings.keyBindJump = vanillaKeys.get("jump");
                return true;
            case "sneak":
                Minecraft.getMinecraft().gameSettings.keyBindSneak = vanillaKeys.get("sneak");
                return true;
            case "sprint":
                Minecraft.getMinecraft().gameSettings.keyBindSprint = vanillaKeys.get("sprint");
                return true;
            case "inventory":
                Minecraft.getMinecraft().gameSettings.keyBindInventory = vanillaKeys.get("inventory");
                return true;
            case "swap_hands":
                Minecraft.getMinecraft().gameSettings.keyBindSwapHands = vanillaKeys.get("swap_hands");
                return true;
            case "drop":
                Minecraft.getMinecraft().gameSettings.keyBindDrop = vanillaKeys.get("drop");
                return true;
            case "use":
                Minecraft.getMinecraft().gameSettings.keyBindUseItem = vanillaKeys.get("use");
                return true;
            case "attack":
                Minecraft.getMinecraft().gameSettings.keyBindAttack = vanillaKeys.get("attack");
                return true;
            case "pick_block":
                Minecraft.getMinecraft().gameSettings.keyBindPickBlock = vanillaKeys.get("pick_block");
                return true;
            case "chat":
                Minecraft.getMinecraft().gameSettings.keyBindChat = vanillaKeys.get("chat");
                return true;
            case "player_list":
                Minecraft.getMinecraft().gameSettings.keyBindPlayerList = vanillaKeys.get("player_list");
                return true;
            case "command":
                Minecraft.getMinecraft().gameSettings.keyBindCommand = vanillaKeys.get("command");
                return true;
            case "screenshot":
                Minecraft.getMinecraft().gameSettings.keyBindScreenshot = vanillaKeys.get("screenshot");
                return true;
            case "toggle_perspective":
                Minecraft.getMinecraft().gameSettings.keyBindTogglePerspective = vanillaKeys.get("toggle_perspective");
                return true;
            case "smooth_camera":
                Minecraft.getMinecraft().gameSettings.keyBindSmoothCamera = vanillaKeys.get("smooth_camera");
                return true;
            case "fullscreen":
                Minecraft.getMinecraft().gameSettings.keyBindFullscreen = vanillaKeys.get("fullscreen");
                return true;
            case "spectator_outlines":
                Minecraft.getMinecraft().gameSettings.keyBindSpectatorOutlines = vanillaKeys.get("spectator_outlines");
                return true;
            case "advancements":
                Minecraft.getMinecraft().gameSettings.keyBindAdvancements = vanillaKeys.get("advancements");
                return true;
            case "hotbar_1":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[0] = vanillaKeys.get("hotbar_1");
                return true;
            case "hotbar_2":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[1] = vanillaKeys.get("hotbar_2");
                return true;
            case "hotbar_3":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[2] = vanillaKeys.get("hotbar_3");
                return true;
            case "hotbar_4":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[3] = vanillaKeys.get("hotbar_4");
                return true;
            case "hotbar_5":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[4] = vanillaKeys.get("hotbar_5");
                return true;
            case "hotbar_6":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[5] = vanillaKeys.get("hotbar_6");
                return true;
            case "hotbar_7":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[6] = vanillaKeys.get("hotbar_7");
                return true;
            case "hotbar_8":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[7] = vanillaKeys.get("hotbar_8");
                return true;
            case "hotbar_9":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[8] = vanillaKeys.get("hotbar_9");
                return true;
            case "save_toolbar":
                Minecraft.getMinecraft().gameSettings.keyBindSaveToolbar = vanillaKeys.get("save_toolbar");
                return true;
            case "load_toolbar":
                Minecraft.getMinecraft().gameSettings.keyBindLoadToolbar = vanillaKeys.get("load_toolbar");
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean disableKeybinding(String keyId) {
        switch(keyId) {
            case "forward":
                Minecraft.getMinecraft().gameSettings.keyBindForward = client.getOverrideKey();
                return true;
            case "left":
                Minecraft.getMinecraft().gameSettings.keyBindLeft = client.getOverrideKey();
                return true;
            case "back":
                Minecraft.getMinecraft().gameSettings.keyBindBack = client.getOverrideKey();
                return true;
            case "right":
                Minecraft.getMinecraft().gameSettings.keyBindRight = client.getOverrideKey();
                return true;
            case "jump":
                Minecraft.getMinecraft().gameSettings.keyBindJump = client.getOverrideKey();
                return true;
            case "sneak":
                Minecraft.getMinecraft().gameSettings.keyBindSneak = client.getOverrideKey();
                return true;
            case "sprint":
                Minecraft.getMinecraft().gameSettings.keyBindSprint = client.getOverrideKey();
                return true;
            case "inventory":
                Minecraft.getMinecraft().gameSettings.keyBindInventory = client.getOverrideKey();
                return true;
            case "swap_hands":
                Minecraft.getMinecraft().gameSettings.keyBindSwapHands = client.getOverrideKey();
                return true;
            case "drop":
                Minecraft.getMinecraft().gameSettings.keyBindDrop = client.getOverrideKey();
                return true;
            case "use":
                Minecraft.getMinecraft().gameSettings.keyBindUseItem = client.getOverrideKey();
                return true;
            case "attack":
                Minecraft.getMinecraft().gameSettings.keyBindAttack = client.getOverrideKey();
                return true;
            case "pick_block":
                Minecraft.getMinecraft().gameSettings.keyBindPickBlock = client.getOverrideKey();
                return true;
            case "chat":
                Minecraft.getMinecraft().gameSettings.keyBindChat = client.getOverrideKey();
                return true;
            case "player_list":
                Minecraft.getMinecraft().gameSettings.keyBindPlayerList = client.getOverrideKey();
                return true;
            case "command":
                Minecraft.getMinecraft().gameSettings.keyBindCommand = client.getOverrideKey();
                return true;
            case "screenshot":
                Minecraft.getMinecraft().gameSettings.keyBindScreenshot = client.getOverrideKey();
                return true;
            case "toggle_perspective":
                Minecraft.getMinecraft().gameSettings.keyBindTogglePerspective = client.getOverrideKey();
                return true;
            case "smooth_camera":
                Minecraft.getMinecraft().gameSettings.keyBindSmoothCamera = client.getOverrideKey();
                return true;
            case "fullscreen":
                Minecraft.getMinecraft().gameSettings.keyBindFullscreen = client.getOverrideKey();
                return true;
            case "spectator_outlines":
                Minecraft.getMinecraft().gameSettings.keyBindSpectatorOutlines = client.getOverrideKey();
                return true;
            case "advancements":
                Minecraft.getMinecraft().gameSettings.keyBindAdvancements = client.getOverrideKey();
                return true;
            case "hotbar_1":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[0] = client.getOverrideKey();
                return true;
            case "hotbar_2":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[1] = client.getOverrideKey();
                return true;
            case "hotbar_3":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[2] = client.getOverrideKey();
                return true;
            case "hotbar_4":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[3] = client.getOverrideKey();
                return true;
            case "hotbar_5":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[4] = client.getOverrideKey();
                return true;
            case "hotbar_6":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[5] = client.getOverrideKey();
                return true;
            case "hotbar_7":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[6] = client.getOverrideKey();
                return true;
            case "hotbar_8":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[7] = client.getOverrideKey();
                return true;
            case "hotbar_9":
                Minecraft.getMinecraft().gameSettings.keyBindsHotbar[8] = client.getOverrideKey();
                return true;
            case "save_toolbar":
                Minecraft.getMinecraft().gameSettings.keyBindSaveToolbar = client.getOverrideKey();
                return true;
            case "load_toolbar":
                Minecraft.getMinecraft().gameSettings.keyBindLoadToolbar = client.getOverrideKey();
                return true;
            default:
                return false;
        }
    }

    Set<String> getKeys() {
        return vanillaKeys.keySet();
    }
}
