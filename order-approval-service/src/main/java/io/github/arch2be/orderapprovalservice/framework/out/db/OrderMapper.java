package io.github.arch2be.orderapprovalservice.framework.out.db;

import io.github.arch2be.orderapprovalservice.application.domain.model.CustomerDetails;
import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
class OrderMapper {

    Order toDomain(OrderEntity entity) {
        return entity.toDomain();
    }

    OrderEntity toEntity(Order order) {
        return new OrderEntity(
                order.getUuid(),
                toCustomerDetailsEntity(order.getCustomerDetails()),
                toProductEntitySet(order.getProductToInstall()),
                order.getStatus());
    }

    private Set<ProductEntity> toProductEntitySet(final Set<Product> productToInstall) {
        return productToInstall.stream()
                .map(product -> new ProductEntity(product.type(), product.details()))
                .collect(Collectors.toSet());
    }

    private CustomerDetailsEntity toCustomerDetailsEntity(final CustomerDetails customerDetails) {
        return new CustomerDetailsEntity(
                customerDetails.clientName(),
                customerDetails.clientSurname(),
                customerDetails.installationAddress(),
                customerDetails.preferredInstallationDate(),
                customerDetails.timeSlotDetails());
    }
}
