package ai.whisperoctopus.core.model.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Base class for all entities that require timestamping.
 * This class is used to mark entities that have created and updated timestamps.
 */
@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeStampedEntity extends TenantAwareEntity {

    /**
     * The timestamp when the entity was created.
     * This field is automatically set to the current date and time when the entity is persisted.
     */
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the entity was last updated.
     * This field is automatically set to the current date and time when the entity is updated.
     */
    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
