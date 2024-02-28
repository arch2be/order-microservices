package io.github.arch2be.orderapprovalservice.application.domain.model;

import io.github.arch2be.orderapprovalservice.application.domain.model.exception.OrderDomainException;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class Order {
    private final UUID uuid;
    private final CustomerDetails customerDetails;
    private final Set<Product> productToInstall;

    private OrderStatus status = OrderStatus.WAITING_FOR_APPROVAL;

    public Order(final UUID uuid, final CustomerDetails customerDetails, final Set<Product> productToInstall) {
        this.uuid = uuid;
        this.customerDetails = customerDetails;
        this.productToInstall = productToInstall;
        validate();
    }

    public void validate() {
        if (Objects.isNull(productToInstall) || productToInstall.isEmpty()) {
            throw new OrderDomainException("ProductToInstall must be greater than 0");
        }
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public Set<Product> getProductToInstall() {
        return productToInstall;
    }

    public void approve() {
        status = OrderStatus.APPROVED;
    }

    public boolean isApproved() {
        return status == OrderStatus.APPROVED;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public UUID getUuid() {
        return uuid;
    }
}
