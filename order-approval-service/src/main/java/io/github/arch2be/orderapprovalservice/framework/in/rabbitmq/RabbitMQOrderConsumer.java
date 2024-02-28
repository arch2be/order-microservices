package io.github.arch2be.orderapprovalservice.framework.in.rabbitmq;

import io.github.arch2be.orderapprovalservice.application.domain.OnNewOrderEventUseCase;
import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.port.out.FetchAgentEmailPort;
import io.github.arch2be.orderapprovalservice.application.port.out.OnNewOrderHandlerPort;
import io.github.arch2be.orderapprovalservice.application.port.out.OrderRepositoryPort;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
class RabbitMQOrderConsumer {
    private final OnNewOrderEventUseCase onNewOrderEventUseCase;

    RabbitMQOrderConsumer(final OrderRepositoryPort orderRepositoryPort,
                          final OnNewOrderHandlerPort onNewOrderHandlerPort,
                          final FetchAgentEmailPort fetchAgentEmailPort) {
        this.onNewOrderEventUseCase = new OnNewOrderEventUseCase(orderRepositoryPort, onNewOrderHandlerPort, fetchAgentEmailPort);
    }

    @RabbitListener(queues = "${new-orders-queue}")
    void consume(@Payload Order order) {
        onNewOrderEventUseCase.process(order);
    }
}
