package spring.sys.train.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.models.Ticket;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TicketCommandToTicketTest {
    public static final Long ID_VALUE = new Long(1L);

    public static final String STATUS = new String("paid");
    public static final Long PRICE = new Long(200L);
    public static final String TYPE= new String("Ger");
    public static final Date DATE = new Date();

    TicketCommandToTicket converter;
    @BeforeEach
    void setUp() {
        converter =new TicketCommandToTicket();
    }
    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() throws Exception {
        TicketCommand ticketCommand = new TicketCommand();
        ticketCommand.setId(ID_VALUE);
        ticketCommand.setStatus(STATUS);
        ticketCommand.setDate(DATE);
        ticketCommand.setType(TYPE);
        ticketCommand.setPrice(PRICE);
        Ticket ticket= converter.convert(ticketCommand);
        assertNotNull(ticket);
        assertEquals(ID_VALUE,ticket.getId());
        assertEquals(TYPE,ticket.getType());
        assertEquals(STATUS,ticket.getStatus());
        assertEquals(PRICE,ticket.getPrice());
    }
}