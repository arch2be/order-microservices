package io.github.arch2be.orderapprovalservice.common;

import io.github.arch2be.orderapprovalservice.application.domain.model.CustomerDetails;
import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.domain.model.Product;
import io.github.arch2be.orderapprovalservice.application.domain.model.ProductType;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

public class OrderUtils {

    public static Order testOrder(UUID uuid) {
        return new Order(uuid, testCustomerDetails(), Collections.singleton(testProduct()));
    }

    public static CustomerDetails testCustomerDetails() {
        return new CustomerDetails(
                "client-name",
                "client-surname",
                "test-address",
                LocalDateTime.now().plusDays(3),
                "timeslot");
    }

    public static Product testProduct() {
        return new Product(ProductType.TV, "just test details");
    }
}
