package ai.whisperoctopus.core.model.entity.tenant;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("AGENT")
public class AgentUser extends User {
    /**
     * The ID of the agent associated with this user.
     * This field is used to link the user to a specific agent in the system.
     */
    @Column(name = "agent_id", nullable = false, columnDefinition = "VARCHAR(255)")
    private String agentId;

    /**
     * The domain of the agent.
     * This field is used to store the agent's domain in the system.
     */
    @Column(name = "domain", nullable = false, columnDefinition = "VARCHAR(255)")
    private String domain;
}
