package ai.whisperoctopus.core.exception.tenant;

public class TenantIsolationException extends RuntimeException {
    public TenantIsolationException(String message) {
        super(message);
    }
}
