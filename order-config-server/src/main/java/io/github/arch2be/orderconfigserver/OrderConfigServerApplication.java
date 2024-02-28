package io.github.arch2be.orderconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class OrderConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderConfigServerApplication.class, args);
    }

}
