package ai.whisperoctopus.core.model.entity.tenant;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("SYSTEM")
public class SystemUser extends User {
    /**
     * The role of the system user.
     * This field is used to store the user's role in the system.
     */
    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(255)")
    private String role;
}
