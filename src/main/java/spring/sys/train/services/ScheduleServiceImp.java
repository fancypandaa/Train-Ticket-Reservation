package spring.sys.train.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.models.Schedule;

import java.util.Set;
@Slf4j
@Service
public class ScheduleServiceImp implements ScheduleService{
    @Override
    public Set<Schedule> getSchedules() {
        return null;
    }

    @Override
    public Schedule findScheduleById(Long Id) {
        return null;
    }

    @Override
    public ScheduleCommand findScheduleByTrainId(Long trainId) {
        return null;
    }
}
