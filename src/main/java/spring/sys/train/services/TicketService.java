package spring.sys.train.services;

import spring.sys.train.commands.TicketCommand;
import spring.sys.train.models.Ticket;
import java.util.*;
public interface TicketService {
    Set<Ticket> listTickets();
    Ticket findTicketById(Long Id);
    TicketCommand findTicketByUserId(Long userId);
    TicketCommand createTicket(TicketCommand ticketCommand);
}
