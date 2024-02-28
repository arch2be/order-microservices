package io.github.arch2be.orderapprovalservice.framework.out.db;

import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
class OrderDAO implements OrderRepositoryPort {
    private final OrderEntityRepository orderEntityRepository;
    private final OrderMapper orderMapper;

    OrderDAO(final OrderEntityRepository orderEntityRepository, final OrderMapper orderMapper) {
        this.orderEntityRepository = orderEntityRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public Order save(final Order order) {
        return orderEntityRepository
                .save(orderMapper.toEntity(order))
                .toDomain();
    }

    @Override
    public Optional<Order> findById(final UUID uuid) {
        return orderEntityRepository
                .findById(uuid)
                .map(orderMapper::toDomain);
    }
}
