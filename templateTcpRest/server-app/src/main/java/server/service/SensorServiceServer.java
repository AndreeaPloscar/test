package server.service;

import common.converter.SensorConverter;
import common.dto.SensorDto;
import common.service.ISensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.model.Sensor;
import server.repository.SensorRepository;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class SensorServiceServer implements ISensorService {

    @Autowired
    private SensorRepository sensorRepository;

    private final ExecutorService executorService;

    public SensorServiceServer( ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Future<String> sendMeasurements(SensorDto sensor) throws Exception {
        return executorService.submit(() ->
                this.sensorRepository.save(Sensor.builder().name(sensor.getName()).measurement(sensor.getMeasurement()).time(new Date().getTime()).build()).toString());
    }
}
