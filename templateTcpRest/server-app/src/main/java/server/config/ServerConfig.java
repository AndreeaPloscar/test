package server.config;

import common.utils.RemotingConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import server.service.SensorServiceServer;
import server.tcp.TcpServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ServerConfig {

    @Bean
    ExecutorService executorService() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Bean
    SensorServiceServer severService() {
        return new SensorServiceServer(executorService());
    }

    @Bean
    TcpServer tcpServer() {
        return new TcpServer(executorService());
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
