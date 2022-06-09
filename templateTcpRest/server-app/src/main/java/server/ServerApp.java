package server;

import common.converter.SensorConverter;
import common.message.Message;
import common.service.ISensorService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import server.service.SensorServiceServer;
import server.tcp.TcpServer;

public class ServerApp {
    public static void main(String[] argv) {
        System.out.println("Starting server");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("server/config");

        ISensorService service = context.getBean(SensorServiceServer.class);

        TcpServer tcpServer = context.getBean(TcpServer.class);

        tcpServer.addHandler(SensorServiceServer.SEND_MEASUREMENTS, request -> {
            try {
                return new Message(Message.OK, service.sendMeasurements(SensorConverter.sensorFromMessage(request.getBody())).get());
            } catch (Exception e) {
                e.printStackTrace();
                return new Message(Message.ERROR, e.getMessage());
            }
        });

        tcpServer.startServer();
    }
}
