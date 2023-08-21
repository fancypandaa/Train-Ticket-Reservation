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

class TrainCommandToTrainTest {
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
    TrainCommandToTrain converter;
    @BeforeEach
    void setUp() {
        converter = new TrainCommandToTrain(new TrainStatusCommandToTrainStatus(),new ScheduleCommandToSchedule(),new TicketCommandToTicket());
    }

    @Test
    void convert() {
        TrainCommand trainCommand= new TrainCommand();
        trainCommand.setId(ID_VALUE);
        trainCommand.setType(TYPE);
        trainCommand.setCapacity(CAPACITY);
        trainCommand.setDestinationCity(DESTINATION_CITY);
        trainCommand.setCurrentCity(CURRENT_CITY);
        trainCommand.setArriveStation(ARRIVAL_STATION);
        trainCommand.setDepartStation(DEPART_STATION);
        TrainStatusCommand trainStatus=new TrainStatusCommand();
        trainStatus.setStatusId(ID_VALUE_STATUS);
        trainCommand.setTrainStatus(trainStatus);
        ScheduleCommand schedule=new ScheduleCommand();
        schedule.setId(ID_SCHEDULE);
        trainCommand.getSchedules().add(schedule);
        TicketCommand ticket=new TicketCommand();
        ticket.setId(ID_TIC);
        trainCommand.getTickets().add(ticket);
        Train train1=converter.convert(trainCommand);
        assertNotNull(train1);
        assertEquals(ID_VALUE,train1.getId());
        assertEquals(CAPACITY,train1.getCapacity());
        assertEquals(ID_VALUE_STATUS,train1.getTrainStatus().getStatusId());
        assertEquals(1,train1.getSchedules().size());
    }

}