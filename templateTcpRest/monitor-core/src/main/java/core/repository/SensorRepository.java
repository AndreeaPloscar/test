package core.repository;


import core.model.Sensor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SensorRepository extends IRepository<Sensor, Long> {
    List<Sensor> findAllByNameContaining(String name);

    @Query(" select distinct s.name from Sensor s")
    List<String> getNames();

    //@Query(" select distinct s from Sensor s where s.name = ?1 order by s.time desc")
    List<Sensor> findTop4ByNameEqualsOrderByTimeDesc(String name);

}
