package spring.sys.train.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.commands.TrainStatusCommand;
import spring.sys.train.models.Departure;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Train;
import spring.sys.train.models.TrainStatus;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TrainStatusCommandToTrainStatusTest {
    public static final Long ID_VALUE = new Long(1L);

    public static final Date lastUpdate = new Date();
    public static final Long availableSeats = new Long(150L);
    public static final Departure departure = Departure.DELAY;
    TrainStatusCommandToTrainStatus converter;
    @BeforeEach
    void setUp() {
        converter= new TrainStatusCommandToTrainStatus();
    }
    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }
    @Test
    void convert() {
        TrainStatusCommand trainStatusCommand = new TrainStatusCommand();
        trainStatusCommand.setStatusId(ID_VALUE);
        trainStatusCommand.setDeparture(departure);
        trainStatusCommand.setAvailableSeats(availableSeats);
        TrainStatus trainStatus= converter.convert(trainStatusCommand);
        assertNotNull(trainStatus);
        assertEquals(ID_VALUE,trainStatus.getStatusId());
        assertEquals(departure,trainStatus.getDeparture());
        assertEquals(availableSeats,trainStatus.getAvailableSeats());
    }
}