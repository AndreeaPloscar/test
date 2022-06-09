package common.converter;

import common.dto.SensorDto;

public class SensorConverter {

    public static String stringForMessage(SensorDto sensor){
        return sensor.getId() + "," + sensor.getName() + "," + sensor.getMeasurement();
    }

    public static SensorDto sensorFromMessage(String message){
        String[] tokens = message.split(",");
        return new SensorDto(Integer.parseInt(tokens[0]), tokens[1], Double.parseDouble(tokens[2]));
    }

}
