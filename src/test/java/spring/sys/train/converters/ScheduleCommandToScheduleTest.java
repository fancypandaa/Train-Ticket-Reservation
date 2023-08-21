package spring.sys.train.converters;

import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Train;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class ScheduleCommandToScheduleTest {
    public static final Train TRAIN =new Train();
    public static final Long ID_VALUE = new Long(1L);

    public static final Date departed = new Date();
    public static final Date arrived = new Date();
    ScheduleCommandToSchedule converter;
    @BeforeEach
    void setUp() {
        converter =new ScheduleCommandToSchedule();
    }
    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() throws Exception {
        ScheduleCommand scheduleCommand = new ScheduleCommand();
        scheduleCommand.setId(ID_VALUE);
        scheduleCommand.setDeparted(departed);
        scheduleCommand.setArrived(arrived);
        Schedule schedule= converter.convert(scheduleCommand);
        assertNotNull(schedule);
        assertEquals(ID_VALUE,schedule.getId());
        assertEquals(departed,schedule.getDeparted());
        assertEquals(arrived,schedule.getArrived());
    }
}