package spring.sys.train.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import spring.sys.train.api.v1.modelsDTO.TicketDTO;
import spring.sys.train.models.Ticket;

@Mapper
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);
    TicketDTO ticketToTicketDTO(Ticket ticket);
    Ticket ticketDTOToticket(TicketDTO ticketDTO);
}
