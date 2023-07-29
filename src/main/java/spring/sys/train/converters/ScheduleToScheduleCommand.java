package spring.sys.train.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.models.Schedule;
@Component
public class ScheduleToScheduleCommand implements Converter<Schedule, ScheduleCommand> {
    @Override
    @Nullable
    @Synchronized
    public ScheduleCommand convert(Schedule source) {
        if(source==null) {
            return null;
        }
        ScheduleCommand scheduleCommand =new ScheduleCommand();
        scheduleCommand.setId(source.getId());
        if(source.getTrain() != null){
            scheduleCommand.setTrainId(source.getTrain().getId());
        }
        scheduleCommand.setArrived(source.getArrived());
        scheduleCommand.setDeparted(source.getDeparted());
        return scheduleCommand;
    }
}
