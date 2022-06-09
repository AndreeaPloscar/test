package web.controller;


import core.model.Sensor;
import core.service.SensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    Logger logger = LoggerFactory.getLogger(SensorController.class);

    @Autowired
    SensorService sensorService;

    @GetMapping("/all")
    List<Sensor> getAll(){
        logger.info("In all");
        return sensorService.findAll();
    }

    @GetMapping("/names")
    List<String> getNames(){
        logger.info("In names");
        return sensorService.getSensorNames();
    }

    @GetMapping("/{name}")
    List<Sensor> getByName(@PathVariable String name){
        logger.info("In names");
        return sensorService.getByName(name);
    }

    @PostMapping("/stop/{name}")
    void stop(@PathVariable String name){
        sensorService.stopSensor(name);
    }

    @GetMapping("/isActive/{name}")
    Boolean isActive(@PathVariable String name){
        if(Objects.equals(name, "")){
            return Boolean.TRUE;
        }
        System.out.println(sensorService.isActive(name));
        return sensorService.isActive(name);
    }

}
