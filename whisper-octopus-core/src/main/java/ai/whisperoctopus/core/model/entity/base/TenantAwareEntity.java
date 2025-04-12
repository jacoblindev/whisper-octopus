package ai.whisperoctopus.core.model.entity.base;

import ai.whisperoctopus.core.util.tenant.TenantContext;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

/**
 * Base class for all entities that are tenant-aware.
 * This class is used to mark entities that are associated with a specific tenant.
 */
@Setter
@Getter
@MappedSuperclass
public abstract class TenantAwareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tenant_aware_seq")
    @SequenceGenerator(
            name = "tenant_entity_seq_generator",
            sequenceName = "#{entityName}_seq", // Use the entity name as the sequence name
            allocationSize = 1
    )
    private Long id;
    /**
     * The ID of the tenant associated with this entity.
     * This field is automatically set to the current tenant ID when the entity is persisted.
     * If no tenant ID is found in the context, it defaults to "system".
     */
    @Column(name = "tenant_id", nullable = false, columnDefinition = "VARCHAR(255)")
    @ColumnDefault("system")
    @Comment("The ID of the tenant associated with this entity")
    private String tenantId;

    /**
     * Sets the tenant ID for this entity before it is persisted.
     * This method is called automatically by the JPA provider before the entity is saved to the database.
     */
    @PrePersist
    protected void onCreate() {
        // Set the tenant ID to the current tenant ID from the context or default to "system"
        this.tenantId = TenantContext.hasTenant() ? TenantContext.getTenantId() : "system";
    }
}
