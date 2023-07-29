package spring.sys.train.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.models.Ticket;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TicketToTicketCommandTest {

    public static final Long ID_VALUE = new Long(1L);

    public static final String STATUS = new String("paid");
    public static final Long PRICE = new Long(200L);
    public static final String TYPE= new String("Ger");
    public static final Date DATE = new Date();

    TicketToTicketCommand converter;
    @BeforeEach
    void setUp() {
        converter =new TicketToTicketCommand();
    }
    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void convert() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setId(ID_VALUE);
        ticket.setStatus(STATUS);
        ticket.setDate(DATE);
        ticket.setType(TYPE);
        ticket.setPrice(PRICE);
        TicketCommand ticketCommand= converter.convert(ticket);
        assertNotNull(ticket);
        assertEquals(ID_VALUE,ticketCommand.getId());
        assertEquals(TYPE,ticketCommand.getType());
        assertEquals(STATUS,ticketCommand.getStatus());
        assertEquals(PRICE,ticketCommand.getPrice());
    }
}