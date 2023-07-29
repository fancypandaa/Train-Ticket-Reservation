package spring.sys.train.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.sys.train.commands.TrainStatusCommand;
import spring.sys.train.models.Departure;
import spring.sys.train.models.TrainStatus;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TrainStatusToTrainStatusCommandTest {

    public static final Long ID_VALUE = new Long(1L);

    public static final Date lastUpdate = new Date();
    public static final Long availableSeats = new Long(150L);
    public static final Departure departure = Departure.DELAY;
    TrainStatusToTrainStatusCommand converter;
    @BeforeEach
    void setUp() {
        converter= new TrainStatusToTrainStatusCommand();
    }
    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }
    @Test
    void convert() {
        TrainStatus trainStatus = new TrainStatus();
        trainStatus.setStatusId(ID_VALUE);
        trainStatus.setDeparture(departure);
        trainStatus.setAvailableSeats(availableSeats);
        TrainStatusCommand trainStatusCommand= converter.convert(trainStatus);
        assertNotNull(trainStatusCommand);
        assertEquals(ID_VALUE,trainStatusCommand.getStatusId());
        assertEquals(departure,trainStatusCommand.getDeparture());
        assertEquals(availableSeats,trainStatusCommand.getAvailableSeats());
    }
}