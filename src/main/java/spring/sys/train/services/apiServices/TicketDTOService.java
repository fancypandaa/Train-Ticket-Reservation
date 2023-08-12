package spring.sys.train.services.apiServices;

import spring.sys.train.api.v1.modelsDTO.TicketDTO;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.models.Ticket;

import java.util.Set;

public interface TicketDTOService {
    Set<TicketDTO> listTickets();
    TicketDTO findTicketById(Long Id);
    TicketDTO saveTicketByDTO(Long Id,TicketDTO ticketDTO);
    TicketDTO createTicket(TicketDTO ticketDTO);
    TicketDTO patchTicketByDTO(Long Id,TicketDTO ticketDTO);
    void cancelTicket(Long ticketId);
}
