package ai.whisperoctopus.core.model.entity.system;

import ai.whisperoctopus.core.model.enumeration.EntityStatus;
import ai.whisperoctopus.core.model.enumeration.TenantType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "tenant")
@Comment("The tenant entity represents a single tenant in the system.")
@EntityListeners(AuditingEntityListener.class)
public class Tenant {
    /**
     * The unique identifier for the tenant.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    /**
     * The name of the tenant.
     * This field is used to store the name of the tenant in the system.
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    /**
     * The description of the tenant.
     * This field is used to store a brief description of the tenant in the system.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * The type of the tenant.
     * This field is used to categorize the tenant into different types.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)")
    private TenantType type;

    /**
     * The status of the tenant.
     * This field is used to indicate whether the tenant is active or inactive.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(10)")
    private EntityStatus status;

    /**
     * The date and time when the tenant was created.
     * This field is automatically set to the current date and time when the entity is persisted.
     */
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The date and time when the tenant was last updated.
     * This field is automatically set to the current date and time when the entity is updated.
     */
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
