package spring.sys.train.api.v1.modelsDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class TicketListDTO {
    Set<TicketDTO> ticketDTOS;
}
