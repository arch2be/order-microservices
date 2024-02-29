package io.github.arch2be.orderapprovalservice.framework.out.rest;

import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.port.out.OnApproveOrderHandlerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("!dev")
class OnApproveOrderHandler implements OnApproveOrderHandlerPort {
    private static final Logger log = LoggerFactory.getLogger(OnApproveOrderHandler.class);
    private final RestTemplate restTemplate;
    @Value("${onapproveorder.out.rest.url}")
    private String url;

    OnApproveOrderHandler(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void handle(final Order order) {
        log.info("New approved order with uuid: " + order.getUuid().toString() + " ready for send to external sys.");
        restTemplate.postForObject(url, order, Order.class);
    }
}
