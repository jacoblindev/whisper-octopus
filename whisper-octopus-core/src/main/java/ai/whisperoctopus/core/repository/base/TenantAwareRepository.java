package ai.whisperoctopus.core.repository.base;

import ai.whisperoctopus.core.model.entity.base.TenantAwareEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Base repository interface for all tenant-aware entities.
 * Automatically filters all database operations by the current tenant context.
 *
 * @param <T> the entity type - must extend TenantAwareEntity
 * @param <S> the ID type of the entity
 */
@NoRepositoryBean
public interface TenantAwareRepository<T extends TenantAwareEntity, S> extends JpaRepository<T, S> {

    /**
     * Overrides the findAll method to automatically filter by tenant.
     * Uses SpEL to inject the current tenant ID from the TenantContext.
     * If the context is system admin, it returns all entities.
     */
    @Override
    @Nonnull
    @Query("SELECT e FROM #{#entityName} e WHERE e.tenantId = :#{@tenantContext.getTenantId()} OR :#{@tenantContext.isSystemContext()} = true")
    List<T> findAll();

    /**
     * Overrides the findById method to ensure cross-tenant data access is prevented.
     * Returns the entity only if it belongs to the current tenant.
     * If the context is system admin, it returns the entity regardless of tenant.
     */
    @Override
    @Nonnull
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND (e.tenantId = :#{@tenantContext.getTenantId()} OR :#{@tenantContext.isSystemContext()} = true)")
    Optional<T> findById(@Nonnull @Param("id") S s);

    /**
     * Safely deletes an entity by ID, ensuring it belongs to the current tenant.
     * If the context is system admin, it allows deletion regardless of tenant.
     */
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id = :id AND (e.tenantId = :#{@tenantContext.getTenantId()} OR :#{@tenantContext.isSystemContext()} = true)")
    void deleteById(@Nonnull @Param("id") S s);

    /**
     * Counts all entities belonging to the current tenant.
     * If the context is system admin, it counts all entities.
     */
    @Override
    @Query("SELECT COUNT(e) FROM #{#entityName} e WHERE e.tenantId = :#{@tenantContext.getTenantId()} OR :#{@tenantContext.isSystemContext()} = true")
    long count();

    /**
     * Checks if an entity with the given ID exists in the current tenant.
     * If the context is system admin, it checks across all tenants.
     */
    @Override
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END FROM #{#entityName} e " +
            "WHERE e.id = :id AND (e.tenantId = :#{@tenantContext.getTenantId()} OR :#{@tenantContext.isSystemContext()} = true)")
    boolean existsById(@Nonnull @Param("id") S s);

    /**
     * Finds all entities by a list of IDs, ensuring they belong to the current tenant.
     * If the context is system admin, it allows fetching entities regardless of tenant.
     */
    @Override
    @Nonnull
    @Query("SELECT e FROM #{#entityName} e WHERE e.id IN :ids AND (e.tenantId = :#{@tenantContext.getTenantId()} OR :#{@tenantContext.isSystemContext()} = true)")
    List<T> findAllById(@Nonnull @Param("ids") Iterable<S> ids);

    /**
     * Custom implementation required in repository classes that extend this interface.
     * When saving an entity, we need to:
     * 1. Verify it doesn't belong to another tenant (if ID is provided)
     * 2. Set the current tenant ID if not already set
     * 3. Save the entity
     * 4. Return the saved entity
     */
    @Override
    @Nonnull
    <C extends T> C save(@Nonnull C entity);

    /**
     * Custom implementation required for saveAll to enforce tenant checks.
     * This method should iterate over the entities, check their tenant IDs,
     * set the current tenant ID if not already set, and save them.
     */
    @Override
    @Nonnull
    <C extends T> List<C> saveAll(@Nonnull Iterable<C> entities);

    /**
     * Custom implementation required for delete to enforce tenant boundaries.
     * This method should check if the entity belongs to the current tenant
     * and delete it if so.
     */
    @Override
    void delete(@Nonnull T entity);

    /**
     * Custom implementation required for deleteAllById to enforce tenant boundaries.
     * This method should check if the entities belong to the current tenant
     * and delete them if so.
     */
    @Override
    void deleteAllById(@Nonnull Iterable<? extends S> ids);
}