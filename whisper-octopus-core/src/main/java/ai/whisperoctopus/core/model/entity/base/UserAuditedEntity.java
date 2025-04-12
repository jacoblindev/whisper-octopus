package ai.whisperoctopus.core.model.entity.base;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class UserAuditedEntity extends TimeStampedEntity {
    /**
     * The ID of the user who created this entity.
     * This field is automatically set to the current user ID when the entity is persisted.
     */
    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    private String createdBy;

    /**
     * The ID of the user who last updated this entity.
     * This field is automatically set to the current user ID when the entity is updated.
     */
    @LastModifiedBy
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;
}
