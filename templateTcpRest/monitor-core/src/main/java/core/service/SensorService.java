package core.service;


import core.model.Sensor;

import java.util.List;

public interface SensorService {

    List<Sensor> findAll();

    List<Sensor> getByName(String name);

    List<String> getSensorNames();

    void stopSensor(String name);
    Boolean isActive(String name);
}
