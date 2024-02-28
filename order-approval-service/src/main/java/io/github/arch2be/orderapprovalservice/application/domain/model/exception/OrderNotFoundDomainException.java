package io.github.arch2be.orderapprovalservice.application.domain.model.exception;

public class OrderNotFoundDomainException extends DomainException {
    public OrderNotFoundDomainException(final String message) {
        super(message);
    }
}
