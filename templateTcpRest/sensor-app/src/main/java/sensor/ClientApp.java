package sensor;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sensor.ui.Ui;

import java.util.concurrent.ExecutorService;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=
                new AnnotationConfigApplicationContext("sensor.config");
        ExecutorService executorService = context.getBean(ExecutorService.class);
        Ui clientConsole = context.getBean(Ui.class);
        clientConsole.run();
        System.out.println("bye client");
        executorService.shutdown();
    }
}
