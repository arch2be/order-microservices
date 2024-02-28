package io.github.arch2be.orderapprovalservice.application.port.out;

import io.github.arch2be.orderapprovalservice.application.domain.model.Order;

@FunctionalInterface
public interface OnApproveOrderHandlerPort {

    void handle(Order order);
}
