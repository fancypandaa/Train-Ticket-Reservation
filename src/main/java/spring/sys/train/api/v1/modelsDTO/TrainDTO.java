package spring.sys.train.api.v1.modelsDTO;

import lombok.Data;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.commands.TrainStatusCommand;

import java.util.HashSet;
import java.util.Set;

@Data
public class TrainDTO {
    private Long Id;
    private String departStation;
    private String arriveStation;
    private String type;
    private Long capacity;
    private String currentCity;
    private String destinationCity;
    private Set<TicketCommand> tickets= new HashSet<>();
    private TrainStatusCommand trainStatus;
    private Set<ScheduleCommand> schedules=new HashSet<>();
}
