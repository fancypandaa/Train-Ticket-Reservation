package spring.sys.train.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Train;

@Component
public class ScheduleCommandToSchedule implements Converter<ScheduleCommand, Schedule> {

    @Override
    @Nullable
    public Schedule convert(ScheduleCommand source) {
        if(source == null){
            return null;
        }
        final Schedule schedule=new Schedule();
        schedule.setId(source.getId());
        if(source.getTrainId()!=null){
            Train train= new Train();
            train.setId(source.getTrainId());
            schedule.setTrain(train);
            train.addSchedule(schedule);
        }
        schedule.setArrived(source.getArrived());
        schedule.setDeparted(source.getDeparted());
        return schedule;
    }
}
