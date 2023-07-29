package spring.sys.train.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.sys.train.models.Departure;
import spring.sys.train.models.Train;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TrainStatusCommand {
    private long statusId;
    private Train train;
    private Date lastUpdate;
    private Long availableSeats;
    private Departure departure;
}
