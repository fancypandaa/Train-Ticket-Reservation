package spring.sys.train.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Train;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleToScheduleCommandTest {

    public static final Train TRAIN =new Train();
    public static final Long ID_VALUE = new Long(1L);

    public static final Date departed = new Date();
    public static final Date arrived = new Date();
    ScheduleToScheduleCommand converter;
    @BeforeEach
    void setUp() {
        converter =new ScheduleToScheduleCommand();
    }
    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setId(ID_VALUE);
        schedule.setDeparted(departed);
        schedule.setArrived(arrived);
        ScheduleCommand scheduleCommand= converter.convert(schedule);
        assertNotNull(scheduleCommand);
        assertEquals(ID_VALUE,scheduleCommand.getId());
        assertEquals(departed,scheduleCommand.getDeparted());
        assertEquals(arrived,scheduleCommand.getArrived());
    }
}