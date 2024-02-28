package io.github.arch2be.orderapprovalservice.framework.out.db;

import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.domain.model.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.springframework.data.annotation.PersistenceCreator;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity(name = "order_table")
class OrderEntity {

    @Id
    private UUID uuid;
    @OneToOne(cascade = CascadeType.ALL)
    private CustomerDetailsEntity customerDetails;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<ProductEntity> productToInstall;

    private OrderStatus status;

    OrderEntity(final UUID uuid, final CustomerDetailsEntity customerDetails, final Set<ProductEntity> productToInstall, final OrderStatus status) {
        this.uuid = uuid;
        this.customerDetails = customerDetails;
        this.productToInstall = productToInstall;
        this.status = status;
    }

    @PersistenceCreator
    public OrderEntity() {
    }

    Order toDomain() {
        return new Order(
                uuid, customerDetails.toDomain(),
                productToInstall.stream()
                        .map(ProductEntity::toDomain)
                        .collect(Collectors.toSet())
        );
    }
}
