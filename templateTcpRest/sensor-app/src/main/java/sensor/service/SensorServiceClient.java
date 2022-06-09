package sensor.service;

import common.converter.SensorConverter;
import common.dto.SensorDto;
import common.message.Message;
import common.service.ISensorService;
import sensor.tcp.TcpClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SensorServiceClient implements ISensorService {

    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    public SensorServiceClient(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public Future<String> sendMeasurements(SensorDto sensor) throws Exception {
        Message message = new Message(ISensorService.SEND_MEASUREMENTS, SensorConverter.stringForMessage(sensor));
        Message response = tcpClient.sendAndReceive(message);

        String header = response.getHeader();
        if(header.equals(Message.ERROR)){
            throw new Exception(response.getBody());
        }
        if(header.equals(Message.STOP)){
            throw new Exception("STOP");
        }
        return executorService.submit(() -> Message.OK);
    }
}
