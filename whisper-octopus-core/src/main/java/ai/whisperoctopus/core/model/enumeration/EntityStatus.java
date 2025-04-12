package ai.whisperoctopus.core.model.enumeration;

public enum EntityStatus {
    /**
     * The tenant is active and can use the system.
     */
    ACTIVE,

    /**
     * The tenant is inactive and cannot use the system.
     */
    INACTIVE,

    /**
     * The tenant is suspended and cannot use the system.
     */
    SUSPENDED,

    /**
     * The tenant is deleted and cannot use the system.
     */
    DELETED
}
