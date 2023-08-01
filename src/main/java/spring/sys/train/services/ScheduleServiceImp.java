package spring.sys.train.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.converters.ScheduleCommandToSchedule;
import spring.sys.train.converters.ScheduleToScheduleCommand;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Train;
import spring.sys.train.repositories.ScheduleRepository;
import spring.sys.train.repositories.TrainRepository;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ScheduleServiceImp implements ScheduleService{
    private final ScheduleCommandToSchedule scheduleCommandToSchedule;
    private final ScheduleToScheduleCommand scheduleToScheduleCommand;
    private final TrainRepository trainRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImp(ScheduleCommandToSchedule scheduleCommandToSchedule, ScheduleToScheduleCommand scheduleToScheduleCommand, TrainRepository trainRepository, ScheduleRepository scheduleRepository) {
        this.scheduleCommandToSchedule = scheduleCommandToSchedule;
        this.scheduleToScheduleCommand = scheduleToScheduleCommand;
        this.trainRepository = trainRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Set<Schedule> getSchedules() {
        log.debug("I'm in the Schedule");
        Set<Schedule> schedules=new HashSet<>();
        scheduleRepository.findAll().iterator().forEachRemaining(schedules::add);
        return schedules;
    }

    @Override
    public Schedule findScheduleById(Long Id) {
        Optional<Schedule> scheduleOptional =scheduleRepository.findById(Id);
        if(!scheduleOptional.isPresent()){
            log.error("Schedule Not Found. For ID value: " + Id.toString() );
        }
        return scheduleOptional.get();
    }
    @Override
    @Transactional
    public ScheduleCommand findScheduleByCommandId(Long Id) {
        return scheduleToScheduleCommand.convert(findScheduleById(Id));
    }

    @Override
    @Transactional
    public Set<ScheduleCommand> findSchedulesByTrainId(Long trainId) {
        Optional<Train> trainOptional = trainRepository.findById(trainId);
        if(!trainOptional.isPresent()){
            log.error("Train id not found"+ trainId);
        }
        Train train =trainOptional.get();
        Set<ScheduleCommand> scheduleCommandOptional =train.getSchedules().stream()
                .map(schedule -> scheduleToScheduleCommand.convert(schedule)).collect(Collectors.toSet());

        return scheduleCommandOptional;
    }
}
