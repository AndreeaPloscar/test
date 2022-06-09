package core.service;

import core.model.Sensor;
import core.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    SensorRepository sensorRepository;

    Map<String, Boolean> active = new HashMap<>();


    @Override
    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    @Override
    public List<Sensor> getByName(String name) {
        return sensorRepository.findTop4ByNameEqualsOrderByTimeDesc(name);
    }

    @Override
    public List<String> getSensorNames() {
        List<String> names = sensorRepository.getNames();
        for(String name: names){
            active.put(name, Boolean.TRUE);
        }
        System.out.println(active);
        return names;
    }

    @Override
    public void stopSensor(String name) {
        active.put(name, Boolean.FALSE);
    }

    @Override
    public Boolean isActive(String name) {
        return active.get(name);
    }
}
