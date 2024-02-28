package io.github.arch2be.orderapprovalservice.application.port.out;

import io.github.arch2be.orderapprovalservice.application.domain.model.Order;

@FunctionalInterface
public interface OnNewOrderHandlerPort {

    void notify(Order order, String email);
}
