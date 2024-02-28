package io.github.arch2be.orderapprovalservice.framework.out.email;

import io.github.arch2be.orderapprovalservice.application.port.out.FetchAgentEmailPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
class FetchAgentEmail implements FetchAgentEmailPort {

    @Value("{$agent-emails}")
    private List<String> agentEmails;

    @Override
    public String fetchAgentEmail() {
        return agentEmails.get(0);
    }
}
