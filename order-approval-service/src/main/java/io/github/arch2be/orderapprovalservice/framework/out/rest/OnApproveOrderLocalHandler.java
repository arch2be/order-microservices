package io.github.arch2be.orderapprovalservice.framework.out.rest;

import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.port.out.OnApproveOrderHandlerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
class OnApproveOrderLocalHandler implements OnApproveOrderHandlerPort {
    private static final Logger log = LoggerFactory.getLogger(OnApproveOrderLocalHandler.class);

    @Override
    public void handle(final Order order) {
        log.info("New approved order with uuid: " + order.getUuid().toString() + " ready for send to external sys.");
    }
}
