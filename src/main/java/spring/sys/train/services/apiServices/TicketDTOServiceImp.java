package spring.sys.train.services.apiServices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spring.sys.train.api.v1.mapper.TicketMapper;
import spring.sys.train.api.v1.modelsDTO.TicketDTO;
import spring.sys.train.models.Ticket;
import spring.sys.train.models.Train;
import spring.sys.train.models.User;
import spring.sys.train.repositories.TicketRepository;
import spring.sys.train.repositories.TrainRepository;
import spring.sys.train.repositories.UserRepository;

import java.util.*;
import java.util.stream.*;

@Service
@Slf4j
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
                    TicketDTO ticketDTO= ticketMapper.ticketToTicketDTO(ticket);
                    if(ticket.getTrain().getId()!=null) {
                        ticketDTO.setTrainId(ticket.getTrain().getId());
                    }
                    if(ticket.getUser().getId()!=null){
                        ticketDTO.setUserId(ticket.getUser().getId());
                    }
                    return ticketDTO;
                })
                .orElseThrow(RuntimeException::new);
    }
    private TicketDTO saveAndReturnDTO(Ticket ticket,TicketDTO ticketDTO){
        Optional<User> userOptional = userRepository.findById(ticketDTO.getUserId());

        if(userOptional.isPresent()){
            ticket.setUser(userOptional.get());
        }
        Optional<Train> trainOptional = trainRepository.findById(ticketDTO.getTrainId());
        if(trainOptional.isPresent()){
            ticket.setTrain(trainOptional.get());
        }

        Ticket savedTicket=ticketRepository.save(ticket);
        TicketDTO ticketDTOs =ticketMapper.ticketToTicketDTO(savedTicket);
        ticketDTOs.setTrainId(ticket.getTrain().getId());
        ticketDTOs.setUserId(ticket.getUser().getId());
        return ticketDTO;
    }
    @Override
    public TicketDTO saveTicketByDTO(Long Id, TicketDTO ticketDTO) {
        Ticket ticket= ticketMapper.ticketDTOToticket(ticketDTO);
        ticket.setId(Id);
        return saveAndReturnDTO(ticket,ticketDTO);
    }

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO) {
        return saveAndReturnDTO(ticketMapper.ticketDTOToticket(ticketDTO),ticketDTO);
    }

    @Override
    public TicketDTO patchTicketByDTO(Long Id, TicketDTO ticketDTO) {
        return ticketRepository.findById(Id).map(ticket -> {
            if(ticketDTO.getDate()!= ticket.getDate() && ticketDTO.getDate() != null){
                ticket.setDate(ticketDTO.getDate());
            }
            if(ticketDTO.getStatus() != ticket.getStatus() &&ticketDTO.getStatus() != null){
                ticket.setStatus(ticketDTO.getStatus());
            }
            if(ticketDTO.getPrice() != ticket.getPrice() && ticketDTO.getPrice() != null){
                ticket.setPrice(ticket.getPrice());
            }
            TicketDTO ticketDTos =ticketMapper.ticketToTicketDTO(ticketRepository.save(ticket));

            ticketDTos.setUserId(ticket.getUser().getId());

            return ticketDTO;
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void cancelTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

}
