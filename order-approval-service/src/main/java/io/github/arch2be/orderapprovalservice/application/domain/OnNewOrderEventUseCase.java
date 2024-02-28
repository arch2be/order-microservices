package io.github.arch2be.orderapprovalservice.application.domain;


import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.port.out.FetchAgentEmailPort;
import io.github.arch2be.orderapprovalservice.application.port.out.OrderRepositoryPort;
import io.github.arch2be.orderapprovalservice.application.port.out.OnNewOrderHandlerPort;

public class OnNewOrderEventUseCase {
    private final OrderRepositoryPort orderRepositoryPort;
    private final OnNewOrderHandlerPort onNewOrderHandlerPort;
    private final FetchAgentEmailPort fetchAgentEmailPort;

    public OnNewOrderEventUseCase(final OrderRepositoryPort orderRepositoryPort, final OnNewOrderHandlerPort onNewOrderHandlerPort, final FetchAgentEmailPort fetchAgentEmailPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.onNewOrderHandlerPort = onNewOrderHandlerPort;
        this.fetchAgentEmailPort = fetchAgentEmailPort;
    }

     public void process(Order order) {
         onNewOrderHandlerPort.notify(orderRepositoryPort.save(order), fetchAgentEmailPort.fetchAgentEmail());
    }
}
