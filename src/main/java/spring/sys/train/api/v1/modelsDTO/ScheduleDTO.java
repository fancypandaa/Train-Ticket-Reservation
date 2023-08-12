package spring.sys.train.api.v1.modelsDTO;

import lombok.Data;
import spring.sys.train.commands.TrainCommand;
import spring.sys.train.models.Train;

import java.util.Date;
@Data
public class ScheduleDTO {
    private Long Id;
    private Date departed;
    private Date arrived;
    private Long trainId;
}
