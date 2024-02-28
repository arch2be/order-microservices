package io.github.arch2be.orderapprovalservice.application.port.out;

import io.github.arch2be.orderapprovalservice.application.domain.model.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryPort {

    Order save(Order order);

    Optional<Order> findById(UUID uuid);
}
