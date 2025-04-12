package ai.whisperoctopus.core.model.entity.tenant;

import ai.whisperoctopus.core.model.entity.base.UserAuditedEntity;
import ai.whisperoctopus.core.model.enumeration.EntityStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "user_type",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class User extends UserAuditedEntity {
    @Column(name = "username", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String username;

    @Column(name = "pwd_hash", nullable = false, columnDefinition = "VARCHAR(255)")
    private String pwdHash;

    @Column(name = "display_name", columnDefinition = "VARCHAR(255)")
    private String displayName;

    @Email(message = "Invalid email format")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "last_login_ip")
    private Integer failedLoginAttempts;

    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    private EntityStatus status;
}
