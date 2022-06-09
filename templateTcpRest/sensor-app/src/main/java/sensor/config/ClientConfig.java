package sensor.config;

import common.service.ISensorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sensor.service.SensorServiceClient;
import sensor.tcp.TcpClient;
import sensor.ui.Ui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ClientConfig {
    @Bean
    TcpClient tcpClient() {
        return new TcpClient();
    }

    @Bean
    ExecutorService executorService(){
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @Bean
    ISensorService sensorService() {
        return new SensorServiceClient(executorService(), tcpClient());
    }

    @Bean
    Ui ui() {
        return new Ui(sensorService());
    }
}