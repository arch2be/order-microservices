package io.github.arch2be.orderapprovalservice.framework.in.rest;

import io.github.arch2be.orderapprovalservice.application.domain.OnOrderApproveUseCase;
import io.github.arch2be.orderapprovalservice.application.domain.model.exception.DomainException;
import io.github.arch2be.orderapprovalservice.application.port.out.OnApproveOrderHandlerPort;
import io.github.arch2be.orderapprovalservice.application.port.out.OrderRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/order")
class OrderApprovalController {
    private static final Logger log = LoggerFactory.getLogger(OrderApprovalController.class);
    private final OnOrderApproveUseCase onOrderApproveUseCase;

    OrderApprovalController(final OrderRepositoryPort orderRepositoryPort,
                            final OnApproveOrderHandlerPort onApproveOrderHandlerPort) {
        this.onOrderApproveUseCase = new OnOrderApproveUseCase(orderRepositoryPort, onApproveOrderHandlerPort);
    }

    @PostMapping(value = "/{orderId}/approve")
    void approve(@PathVariable("orderId") UUID orderId) {
        log.info("Start process for approve order with uuid: " + orderId.toString());
        onOrderApproveUseCase.process(orderId);
    }

    @ExceptionHandler({DomainException.class})
    ResponseEntity<String> handleException(DomainException exception) {
        log.info("Handle DomainException with message: " + exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
