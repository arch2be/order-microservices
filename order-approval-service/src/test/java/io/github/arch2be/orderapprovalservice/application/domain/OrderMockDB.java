package io.github.arch2be.orderapprovalservice.application.domain;

import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.port.out.OrderRepositoryPort;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

class OrderMockDB implements OrderRepositoryPort {

    private final Map<UUID, Order> orders = new HashMap<>();

    @Override
    public Order save(final Order order) {
        return orders.put(order.getUuid(), order);
    }

    @Override
    public Optional<Order> findById(final UUID uuid) {
        return Optional.ofNullable(orders.get(uuid));
    }
}
