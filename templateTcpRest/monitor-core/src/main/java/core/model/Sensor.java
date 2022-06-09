package core.model;

import lombok.*;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Sensor extends BaseEntity<Long>{

    private String name;

    private Double measurement;

    private Long time;


}
