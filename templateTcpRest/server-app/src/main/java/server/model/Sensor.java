package server.model;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sensor extends BaseEntity<Long>{
    private String name;
    private Double measurement;
    private Long time;

}
