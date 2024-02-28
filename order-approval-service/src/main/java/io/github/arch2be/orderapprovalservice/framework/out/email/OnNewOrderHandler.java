package io.github.arch2be.orderapprovalservice.framework.out.email;

import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.application.port.out.OnNewOrderHandlerPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
class OnNewOrderHandler implements OnNewOrderHandlerPort {
    private static final Logger log = LoggerFactory.getLogger(OnNewOrderHandler.class);
    private final JavaMailSender emailSender;

    OnNewOrderHandler(final JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void notify(final Order order, final String email) {
        log.info("Send email on address: " + email + " with new order to approve with uuid: " + order.getUuid().toString());
        //emailSender.send(prepareOrderMessage(order, email));
    }

    private SimpleMailMessage prepareOrderMessage(final Order order, final String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@order-approval-service.com");
        message.setTo(email);
        message.setSubject("New Order!!");
        message.setText("Some text with order details");
        return message;
    }
}
