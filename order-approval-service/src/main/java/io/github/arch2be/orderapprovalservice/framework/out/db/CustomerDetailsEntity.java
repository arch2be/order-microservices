package io.github.arch2be.orderapprovalservice.framework.out.db;

import io.github.arch2be.orderapprovalservice.application.domain.model.CustomerDetails;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;

@Entity(name = "customer_details")
class CustomerDetailsEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String clientName;
    private String clientSurname;
    private String installationAddress;
    private LocalDateTime preferredInstallationDate;
    private String timeSlotDetails;

    @PersistenceCreator
    public CustomerDetailsEntity() {
    }

    CustomerDetailsEntity(final String clientName,
                          final String clientSurname,
                          final String installationAddress,
                          final LocalDateTime preferredInstallationDate,
                          final String timeSlotDetails) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.installationAddress = installationAddress;
        this.preferredInstallationDate = preferredInstallationDate;
        this.timeSlotDetails = timeSlotDetails;
    }

    CustomerDetails toDomain() {
        return new CustomerDetails(clientName,
                clientSurname,
                installationAddress,
                preferredInstallationDate,
                timeSlotDetails);
    }
}
