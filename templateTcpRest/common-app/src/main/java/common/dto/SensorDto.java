package common.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorDto {
    private int id;
    private String name;
    private Double measurement;
}
