package io.github.arch2be.orderapprovalservice.application.domain;

import io.github.arch2be.orderapprovalservice.application.domain.model.CustomerDetails;
import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.domain.model.Product;
import io.github.arch2be.orderapprovalservice.application.domain.model.ProductType;
import io.github.arch2be.orderapprovalservice.application.domain.model.exception.OrderNotFoundDomainException;
import io.github.arch2be.orderapprovalservice.common.OrderUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OnOrderApproveUseCaseTest {

    @Test
    void shouldThrowDomainException() {
        OrderMockDB orderMockDB = new OrderMockDB();
        OnOrderApproveUseCase orderApproveUseCase = new OnOrderApproveUseCase(orderMockDB, (order) -> System.out.println());
        UUID noExistsUUIDInDB = UUID.randomUUID();

        OrderNotFoundDomainException thrown = assertThrows(
                OrderNotFoundDomainException.class,
                () -> orderApproveUseCase.process(noExistsUUIDInDB),
                "Expected orderApproveUseCase.process() to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Order with uuid: " + noExistsUUIDInDB + " not exists"));
    }

    @Test
    void shouldCorrectApproveOrder() {
        OrderMockDB orderMockDB = new OrderMockDB();
        OnOrderApproveUseCase orderApproveUseCase = new OnOrderApproveUseCase(orderMockDB, (order) -> System.out.println());
        UUID testUUID = UUID.randomUUID();
        Order testOrder = OrderUtils.testOrder(testUUID);
        orderMockDB.save(testOrder);

        Assertions.assertFalse(testOrder.isApproved());

        orderApproveUseCase.process(testUUID);

        Assertions.assertTrue(testOrder.isApproved());
    }
}