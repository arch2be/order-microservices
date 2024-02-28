package io.github.arch2be.orderapprovalservice.framework;

import io.github.arch2be.orderapprovalservice.OrderApprovalServiceApplication;
import io.github.arch2be.orderapprovalservice.application.domain.model.Order;
import io.github.arch2be.orderapprovalservice.common.OrderUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.util.Base64;
import java.util.UUID;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.verify.VerificationTimes.exactly;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = OrderApprovalServiceApplication.class)
@Testcontainers
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class OnNewOrderITest extends TestContainersConfig {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static ClientAndServer mockServer;

    @BeforeAll
    public static void startServer() {
        mockServer = startClientAndServer(9999);
    }

    @AfterAll
    public static void stopServer() {
        mockServer.stop();
    }

    @Test
    void shouldThrowUnAuthorizeCode() throws Exception {
        mockMvc.perform(post("/api/v1/order/" + UUID.randomUUID() + "/approve")
                        .headers(getHttpHeaders("wrongusername", "wrongpassword"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldCorrectProcessOrderApprove() throws Exception {
        createMockResponseFromExternalServer();

        final UUID testUUID = UUID.randomUUID();
        final Order testOrderToApprove = OrderUtils.testOrder(testUUID);

        rabbitTemplate.convertAndSend("test-queue", testOrderToApprove);

        mockMvc.perform(post("/api/v1/order/" + testUUID + "/approve")
                        .headers(getHttpHeaders("agent", "agent"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verifyPostRequest();
    }

    private HttpHeaders getHttpHeaders(String username, String password) {
        HttpHeaders httpHeaders = new HttpHeaders();
        byte[] encodedBytes = Base64.getEncoder().encode((username + ":" + password).getBytes());

        String USER_PASS = new String(encodedBytes);
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Authorization", "Basic " + USER_PASS);

        return httpHeaders;
    }

    private void verifyPostRequest() {
        new MockServerClient("localhost", 9999).verify(
                request().withMethod("POST").withPath("/order"),
                exactly(1)
        );
    }

    private void createMockResponseFromExternalServer() {
        new MockServerClient("localhost", 9999)
                .when(request().withMethod("POST").withPath("/order"))
                .respond(response().withStatusCode(200));
    }
}
