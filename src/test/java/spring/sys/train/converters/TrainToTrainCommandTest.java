package spring.sys.train.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.commands.TrainCommand;
import spring.sys.train.commands.TrainStatusCommand;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Ticket;
import spring.sys.train.models.Train;
import spring.sys.train.models.TrainStatus;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TrainToTrainCommandTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final Long ID_VALUE_STATUS = new Long(2L);
    public static final Long ID_SCHEDULE = new Long(3L);
    public static final Long ID_TIC = new Long(4L);

    public static final String DEPART_STATION = new String("NY");
    public static final String ARRIVAL_STATION = new String("WC");

    public static final String CURRENT_CITY = new String("NEW YORK");
    public static final String DESTINATION_CITY = new String("NEW GERSiY");
    public static final Long CAPACITY = new Long(200L);
    public static final String TYPE= new String("Ger");
    public static final Date DATE = new Date();
    TrainToTrainCommand converter;
    @BeforeEach
    void setUp() {
        converter = new TrainToTrainCommand(new TrainStatusToTrainStatusCommand(),new ScheduleToScheduleCommand(),new TicketToTicketCommand());
    }

    @Test
    void convert() {
        Train train = new Train();
        train.setId(ID_VALUE);
        train.setType(TYPE);
        train.setCapacity(CAPACITY);
        train.setDestinationCity(DESTINATION_CITY);
        train.setCurrentCity(CURRENT_CITY);
        train.setArriveStation(ARRIVAL_STATION);
        train.setDepartStation(DEPART_STATION);
        TrainStatus train1=new TrainStatus();
        train1.setStatusId(ID_VALUE_STATUS);
        train.setTrainStatus(train1);
        Schedule schedule=new Schedule();
        schedule.setId(ID_SCHEDULE);
        train.getSchedules().add(schedule);
        Ticket ticket=new Ticket();
        ticket.setId(ID_TIC);
        train.getTickets().add(ticket);
        TrainCommand trainCommand=converter.convert(train);
        assertNotNull(train1);
        assertEquals(ID_VALUE,trainCommand.getId());
        assertEquals(CAPACITY,trainCommand.getCapacity());
        assertEquals(ID_VALUE_STATUS,trainCommand.getTrainStatus().getStatusId());
        assertEquals(1,trainCommand.getSchedules().size());
    }
}