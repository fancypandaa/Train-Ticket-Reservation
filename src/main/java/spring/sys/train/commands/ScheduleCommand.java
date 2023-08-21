package spring.sys.train.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.sys.train.models.Train;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class ScheduleCommand {
    private Long Id;
    private Date departed;
    private Date arrived;
    private Long trainId;
}
