package spring.sys.train.services;

import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.models.Schedule;
import java.util.*;
public interface ScheduleService {
    Set<Schedule> getSchedules();
    Schedule findScheduleById(Long Id);
    ScheduleCommand findScheduleByTrainId(Long trainId);

}
