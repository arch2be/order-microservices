package io.github.arch2be.orderapprovalservice.application.domain;


import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.domain.model.exception.OrderNotFoundDomainException;
import io.github.arch2be.orderapprovalservice.application.port.out.OnApproveOrderHandlerPort;
import io.github.arch2be.orderapprovalservice.application.port.out.OrderRepositoryPort;

import java.util.Optional;
import java.util.UUID;

public class OnOrderApproveUseCase {
    private final OrderRepositoryPort orderRepositoryPort;
    private final OnApproveOrderHandlerPort onApproveOrderHandlerPort;

    public OnOrderApproveUseCase(final OrderRepositoryPort orderRepositoryPort,
                                 final OnApproveOrderHandlerPort onApproveOrderHandlerPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.onApproveOrderHandlerPort = onApproveOrderHandlerPort;
    }

    public void process(UUID uuid) {
        final Optional<Order> orderOp = orderRepositoryPort.findById(uuid);

        if (orderOp.isPresent()) {
            final Order order = orderOp.get();

            order.approve();

            final Order approvedOrder = orderRepositoryPort.save(order);
            onApproveOrderHandlerPort.handle(approvedOrder);
        } else {
            throw new OrderNotFoundDomainException("Order with uuid: " + uuid.toString() + " not exists");
        }
    }
}
