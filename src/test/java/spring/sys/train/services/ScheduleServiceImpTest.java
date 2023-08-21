package spring.sys.train.services;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.converters.ScheduleCommandToSchedule;
import spring.sys.train.converters.ScheduleToScheduleCommand;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Train;
import spring.sys.train.repositories.ScheduleRepository;
import spring.sys.train.repositories.TrainRepository;
import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class ScheduleServiceImpTest {
    ScheduleServiceImp scheduleServiceImp;
    @Mock
    ScheduleCommandToSchedule scheduleCommandToSchedule;
    @Mock
    ScheduleToScheduleCommand scheduleToScheduleCommand;
    @Mock
    TrainRepository trainRepository;
    @Mock
    ScheduleRepository scheduleRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        scheduleServiceImp =new ScheduleServiceImp(scheduleCommandToSchedule,scheduleToScheduleCommand,
                trainRepository,scheduleRepository);
    }


    @Test
    void findScheduleById() {
        Schedule schedule=new Schedule();
        schedule.setId(1L);
        Optional<Schedule> scheduleOptional=Optional.of(schedule);
        when(scheduleRepository.findById(anyLong())).thenReturn(scheduleOptional);
        Schedule schedule1=scheduleServiceImp.findScheduleById(1L);
        assertNotNull("Null schedule1 returned",schedule1);
        verify(scheduleRepository,times(1)).findById(anyLong());
        verify(scheduleRepository,never()).findAll();
    }

    @Test
    void findScheduleByCommandId() {
        Schedule schedule=new Schedule();
        schedule.setId(1L);
        Optional<Schedule> scheduleOptional=Optional.of(schedule);
        when(scheduleRepository.findById(anyLong())).thenReturn(scheduleOptional);
        ScheduleCommand scheduleCommand=new ScheduleCommand();
        scheduleCommand.setId(1L);
        when(scheduleToScheduleCommand.convert(any())).thenReturn(scheduleCommand);
        ScheduleCommand scheduleCommand1=scheduleServiceImp.findScheduleByCommandId(1L);
        assertNotNull("null Schedule",scheduleCommand1);
        verify(scheduleRepository,times(1)).findById(anyLong());
        verify(scheduleRepository,never()).findAll();
    }

    @Test
    void getSchedules() {
        Schedule schedule=new Schedule();
        HashSet schedulesData=new HashSet();
        schedulesData.add(schedule);
        when(scheduleServiceImp.getSchedules()).thenReturn(schedulesData);
        Set<Schedule> schedules= scheduleServiceImp.getSchedules();
        assertEquals(schedules.size(),1);
        verify(scheduleRepository,times(1)).findAll();
        verify(scheduleRepository,never()).findById(anyLong());
    }

    @Test
    void findSchedulesByTrainId() {
        Schedule schedule=new Schedule();
        schedule.setId(1L);
        Train train=new Train();
        train.setId(2L);
        train.addSchedule(schedule);
        Optional<Train> train1=Optional.of(train);
        when(trainRepository.findById(anyLong())).thenReturn(train1);
        Set<ScheduleCommand> scheduleCommand =scheduleServiceImp.findSchedulesByTrainId(2L);
        assertEquals(scheduleCommand.size(),1);
        verify(scheduleRepository,never()).findAllById(Collections.singleton(anyLong()));
    }
}