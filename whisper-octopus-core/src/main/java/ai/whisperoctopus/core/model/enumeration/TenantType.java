package ai.whisperoctopus.core.model.enumeration;

public enum TenantType {
    /**
     * The tenant type is Freemium.
     * This type of tenant has limited access to features and resources.
     */
    FREEMIUM,

    /**
     * The tenant type is Paid.
     * This type of tenant has access to premium features and resources.
     */
    PAID,

    /**
     * The tenant type is Enterprise.
     * This type of tenant has access to all features and resources.
     */
    ENTERPRISE,

    /**
     * The tenant type is Trial.
     * This type of tenant has temporary access to features and resources.
     */
    TRIAL,

    /**
     * The tenant type is System.
     * This type of tenant is used for system-level operations and has full access to all features and resources.
     */
    SYSTEM,

    /**
     * The tenant type is Test.
     * This type of tenant is used for testing purposes and may have limited features and resources.
     */
    TEST,

    /**
     * The tenant type is Demo.
     * This type of tenant is used for demonstration purposes and may have limited features and resources.
     */
    DEMO
}
