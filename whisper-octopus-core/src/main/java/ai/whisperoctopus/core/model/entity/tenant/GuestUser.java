package ai.whisperoctopus.core.model.entity.tenant;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("GUEST")
public class GuestUser extends User {
    /**
     * The ID of the session associated with this guest user.
     * This field is used to link the user to a specific session in the system.
     */
    @Column(name = "session_id", nullable = false, columnDefinition = "VARCHAR(255)")
    private String sessionId;

    /**
     * The expiration time of the session associated with this guest user.
     * This field is used to store the expiration time of the user's session in the system.
     */
    @Column(name = "expiry_time", nullable = false, columnDefinition = "TIMESTAMP")
    private String expiryTime;
}
