package ai.whisperoctopus.core.exception.tenant;

public class MissingTenantException extends RuntimeException {
    public MissingTenantException(String message) {
        super(message);
    }
}
