package spring.sys.train.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Ticket;
import spring.sys.train.models.TrainStatus;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TrainCommand {
    private Long Id;
    private String departStation;
    private String arriveStation;
    private String type;
    private Long capacity;
    private String currentCity;
    private String destinationCity;
    private Set<Ticket> tickets= new HashSet<>();
    private TrainStatus trainStatus;
    private Set<Schedule> schedules=new HashSet<>();
}
