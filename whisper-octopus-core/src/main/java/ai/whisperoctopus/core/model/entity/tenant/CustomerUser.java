package ai.whisperoctopus.core.model.entity.tenant;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("CUSTOMER")
public class CustomerUser extends User {
    /**
     * The ID of the customer associated with this user.
     * This field is used to link the user to a specific customer in the system.
     */
    @Column(name = "customer_id", nullable = false, columnDefinition = "VARCHAR(255)")
    private String customerId;

    /**
     * The phone number of the customer.
     * This field is used to store the customer's phone number in the system.
     */
    @Column(name = "phone_number", columnDefinition = "VARCHAR(255)")
    private String phoneNumber;
}
