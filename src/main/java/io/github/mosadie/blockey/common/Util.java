package io.github.mosadie.blockey.common;

public class Util {
    public static String[] splitKeyString(String key) {
        String modId = "minecraft";

        if (key.contains(":")) {
            String[] split = key.split(":");
            modId = split[0];
            key = split[1];
        }

        return new String[] {modId, key};
    }
}