package common.service;

import common.dto.SensorDto;

import java.util.concurrent.Future;

public interface ISensorService {

    String SEND_MEASUREMENTS = "sendMeasurements";

    Future<String> sendMeasurements(SensorDto sensor) throws Exception;
}
