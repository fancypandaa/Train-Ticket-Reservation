package spring.sys.train.api.v1.modelsDTO;

import lombok.Data;
import spring.sys.train.models.Departure;

import java.util.Date;

@Data
public class TrainStatusDTO {
    private long statusId;
    private Date lastUpdate;
    private Long availableSeats;
    private Departure departure;
}
