package ai.whisperoctopus.core.util.tenant;

import ai.whisperoctopus.core.exception.tenant.MissingTenantException;
import org.springframework.stereotype.Component;

/**
 * TenantContext is a utility class that provides a thread-local storage for tenant IDs.
 * It allows setting, getting, and clearing the tenant ID for the current thread.
 * It also provides a way to check if the current context is system admin mode.
 */
@Component
public class TenantContext {
    /**
     * Private constructor to prevent instantiation.
     */
    private TenantContext() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * ThreadLocal variable to store the tenant ID for the current thread.
     */
    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    private static final ThreadLocal<Boolean> SYSTEM_ADMIN = new ThreadLocal<>();

    /**
     * Set the system context for the current thread to enable system admin mode.
     *
     * @param isSystemContext true if the current context is system admin, false otherwise
     */
    public static void setSystemContext(boolean isSystemContext) {
        SYSTEM_ADMIN.set(isSystemContext);
    }

    /**
     * Check if the current context is system admin mode.
     *
     * @return true if the current context is system admin, false otherwise
     */
    public static boolean isSystemContext() {
        return Boolean.TRUE.equals(SYSTEM_ADMIN.get());
    }

    /**
     * Set the tenant ID for the current thread.
     *
     * @param tenantId the tenant ID to set
     */
    public static void setTenantId(String tenantId) {
        CURRENT_TENANT.set(tenantId);
    }

    /**
     * Get the tenant ID for the current thread.
     *
     * @return the tenant ID
     * @throws MissingTenantException if no tenant ID is found in the current context
     */
    public static String getTenantId() {
        // Check if the current context is system admin mode
        if (isSystemContext()) {
            return "system";
        }

        // Get the tenant ID from the thread-local variable
        String tenantId = CURRENT_TENANT.get();
        if (tenantId == null) {
            throw new MissingTenantException("No tenant ID found in current context");
        }
        return tenantId;
    }

    /**
     * Check if a tenant ID is set for the current thread.
     *
     * @return the tenant ID, or null if not set
     */
    public static boolean hasTenant() {
        return CURRENT_TENANT.get() != null;
    }

    /**
     * Clear the tenant ID for the current thread.
     */
    public static void clear() {
        CURRENT_TENANT.remove();
        SYSTEM_ADMIN.remove();
    }
}
