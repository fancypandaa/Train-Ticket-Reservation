package spring.sys.train.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.converters.TicketCommandToTicket;
import spring.sys.train.converters.TicketToTicketCommand;
import spring.sys.train.models.Ticket;
import spring.sys.train.models.User;
import spring.sys.train.repositories.TicketRepository;
import spring.sys.train.repositories.TrainRepository;
import spring.sys.train.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImp implements TicketService{
    private final TicketToTicketCommand ticketToTicketCommand;
    private final TicketCommandToTicket ticketCommandToTicket;
    private  final TicketRepository ticketRepository;
    private final TrainRepository trainRepository;
    private final UserRepository userRepository;

    public TicketServiceImp(TicketToTicketCommand ticketToTicketCommand, TicketCommandToTicket ticketCommandToTicket, TicketRepository ticketRepository, TrainRepository trainRepository, UserRepository userRepository) {
        this.ticketToTicketCommand = ticketToTicketCommand;
        this.ticketCommandToTicket = ticketCommandToTicket;
        this.ticketRepository = ticketRepository;
        this.trainRepository = trainRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Set<Ticket> listTickets() {
        log.debug("I'm in the Ticket");
        Set<Ticket> tickets=new HashSet<>();
        ticketRepository.findAll().iterator().forEachRemaining(tickets::add);
        return tickets;
    }

    @Override
    public Ticket findTicketById(Long Id) {
        Optional<Ticket> ticketOptional =ticketRepository.findById(Id);
        if(!ticketOptional.isPresent()){
            log.error("Ticket Not Found. For ID value: " + Id.toString() );
        }
        return ticketOptional.get();
    }

    @Override
    @Transactional
    public TicketCommand findTicketByCommandId(Long Id) {
        return ticketToTicketCommand.convert(findTicketById(Id));
    }

    @Override
    @Transactional
    public Set<TicketCommand> findTicketByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) {
            log.error("User id not found"+ userId);
        }
        User user = optionalUser.get();
        Set<TicketCommand> ticketCommands = user.getTickets().stream()
                .map(ticket -> ticketToTicketCommand.convert(ticket)).collect(Collectors.toSet());
        return ticketCommands;
    }
    @Override
    @Transactional
    public TicketCommand createTicket(TicketCommand ticketCommand) {
        Ticket ticket=ticketCommandToTicket.convert(ticketCommand);
        Ticket savedTicket = ticketRepository.save(ticket);
        log.debug("save ticket",ticket.getId());
        return ticketToTicketCommand.convert(savedTicket);
    }

    @Override
    public void cancelTicket(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}
