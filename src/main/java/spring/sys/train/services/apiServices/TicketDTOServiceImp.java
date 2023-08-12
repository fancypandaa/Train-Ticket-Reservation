package spring.sys.train.services.apiServices;

import org.springframework.stereotype.Service;
import spring.sys.train.api.v1.mapper.TicketMapper;
import spring.sys.train.api.v1.modelsDTO.TicketDTO;
import spring.sys.train.api.v1.modelsDTO.TicketListDTO;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.models.Ticket;
import spring.sys.train.repositories.TicketRepository;
import spring.sys.train.repositories.TrainRepository;
import spring.sys.train.repositories.UserRepository;
import spring.sys.train.services.TicketService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TicketDTOServiceImp implements TicketDTOService {
    public final TicketMapper ticketMapper;
    public final TicketRepository ticketRepository;
    private final TrainRepository trainRepository;
    private final UserRepository userRepository;

    public TicketDTOServiceImp(TicketMapper ticketMapper, TicketRepository ticketRepository, TrainRepository trainRepository, UserRepository userRepository) {
        this.ticketMapper = ticketMapper;
        this.ticketRepository = ticketRepository;
        this.trainRepository = trainRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<TicketDTO> listTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::ticketToTicketDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public TicketDTO findTicketById(Long Id) {
        return ticketRepository.findById(Id)
                .map(ticket -> {
                    TicketDTO ticketDTO = ticketMapper.ticketToTicketDTO(ticket);
                    ticketDTO.setTrainId(ticket.getTrain().getId());
                    ticketDTO.setUserId(ticket.getUser().getId());
                    return ticketDTO;
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public TicketDTO saveTicketByDTO(Long Id, TicketDTO ticketDTO) {
        Ticket ticket= ticketMapper.TicketDTOToticket(ticketDTO);
        ticket.setId(Id);
        return saveAndReturnDTO(ticket);
    }

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        return saveAndReturnDTO(ticketMapper.TicketDTOToticket(ticketDTO));
    }
    private TicketDTO saveAndReturnDTO(Ticket ticket){
        Ticket savedTicket=ticketRepository.save(ticket);
        TicketDTO ticketDTO =ticketMapper.ticketToTicketDTO(savedTicket);
        ticketDTO.setTrainId(ticket.getTrain().getId());
        ticketDTO.setUserId(ticket.getUser().getId());
        return ticketDTO;
    }
    @Override
    public TicketDTO patchTicketByDTO(Long Id, TicketDTO ticketDTO) {
        return ticketRepository.findById(Id).map(ticket -> {
            if(ticketDTO.getDate()!= null){
                ticketDTO.setDate(ticketDTO.getDate());
            }
            if(ticketDTO.getStatus() != null){
                ticketDTO.setStatus(ticketDTO.getStatus());
            }
            if(ticketDTO.getTrainId() !=null){
                ticketDTO.setTrainId(ticketDTO.getTrainId());
            }
            TicketDTO ticketDTos =ticketMapper.ticketToTicketDTO(ticket);
            ticketDTos.setUserId(ticket.getUser().getId());
            return ticketDTO;
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void cancelTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

}
