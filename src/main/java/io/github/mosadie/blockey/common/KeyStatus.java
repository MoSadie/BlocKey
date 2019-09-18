package io.github.mosadie.blockey.common;

public enum KeyStatus {
    DISABLED,
    ENABLED,
    ERROR;

    public static KeyStatus stringToStatus(String status) {
        switch(status.toLowerCase()) {
            case "disabled":
                return DISABLED;
            case "enabled":
                return ENABLED;
            default:
                return ERROR;
        }
    }

    public String toString() {
        switch(this) {
            case DISABLED:
                return "disabled";
            case ENABLED:
                return "enabled";
            default:
                return "error";
        }
    }
}