package io.github.mosadie.blockey.api;

/**
 * Classes that inplement this are able to handle enabling/disabling keybindings.
 * This interface will only be used client-side.
 */
public interface IBlockableKey {
    /**
     * Enables a keybinding, if not already enabled.
     * If already enabled, does nothing.
     * @param keyId The key ID for the keybinding to enable.
     * @returns False if something went wrong enabling the keybinding, true otherwise.
     */
    public boolean enableKeybinding(String keyId);

    /**
     * Disables a keybinding, if not already disabled.
     * If already disabled, does nothing.
     * @param keyId The key ID for the keybinding to disable.
     * @returns False if something went wrong enabling the keybinding, true otherwise.
     */
    public boolean disableKeybinding(String keyid);
}