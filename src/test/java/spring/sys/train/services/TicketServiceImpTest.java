package spring.sys.train.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.sys.train.commands.ScheduleCommand;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.converters.ScheduleCommandToSchedule;
import spring.sys.train.converters.ScheduleToScheduleCommand;
import spring.sys.train.converters.TicketCommandToTicket;
import spring.sys.train.converters.TicketToTicketCommand;
import spring.sys.train.models.Schedule;
import spring.sys.train.models.Ticket;
import spring.sys.train.repositories.ScheduleRepository;
import spring.sys.train.repositories.TicketRepository;
import spring.sys.train.repositories.TrainRepository;
import spring.sys.train.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TicketServiceImpTest {
    TicketServiceImp ticketServiceImp;
    @Mock
    TicketCommandToTicket ticketCommandToTicket;
    @Mock
    TicketToTicketCommand ticketToTicketCommand;
    @Mock
    TicketRepository ticketRepository;
    @Mock
    TrainRepository trainRepository;
    @Mock
    UserRepository userRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ticketServiceImp = new TicketServiceImp(ticketToTicketCommand,ticketCommandToTicket,ticketRepository,trainRepository
        ,userRepository);
    }

    @Test
    void listTickets() {
        Ticket ticket=new Ticket();
        HashSet ticketData=new HashSet();
        ticketData.add(ticket);
        when(ticketServiceImp.listTickets()).thenReturn(ticketData);
        Set<Ticket> tickets= ticketServiceImp.listTickets();
        assertEquals(tickets.size(),1);
        verify(ticketRepository,times(1)).findAll();
        verify(ticketRepository,never()).findById(anyLong());
    }

    @Test
    void findTicketById() {
        Ticket ticket=new Ticket();
        ticket.setId(1L);
        Optional<Ticket> ticketOptional=Optional.of(ticket);
        when(ticketRepository.findById(anyLong())).thenReturn(ticketOptional);
        Ticket optionalTicket=ticketServiceImp.findTicketById(1L);
        assertNotNull("Null schedule1 returned",optionalTicket);
        verify(ticketRepository,times(1)).findById(anyLong());
        verify(ticketRepository,never()).findAll();
    }

//    @Test
//    void findTicketByUserId() {
//        Ticket ticket=new Ticket();
//        ticket.setId(1L);
//        Optional<Ticket> ticketOptional=Optional.of(ticket);
//        when(ticketServiceImp.findTicketByUserId(anyLong())).thenReturn(ticketOptional);
//        TicketCommand ticketCommand=new TicketCommand();
//        ticketCommand.setId(1L);
//        when(ticketCommandToTicket.convert(any())).thenReturn(ticketCommand);
//        TicketCommand ticketCommand1=ticketServiceImp.findTicketByCommandId(1L);
//        assertNotNull("null Schedule",ticketCommand1);
//        verify(trainRepository,times(1)).findById(anyLong());
//        verify(trainRepository,never()).findAll();
//    }

//    @Test
//    void createTicket() {
//
//    }

    @Test
    void cancelTicket() {
        Long id= Long.valueOf(2L);
        ticketServiceImp.cancelTicket(id);
        verify(ticketRepository,times(1)).deleteById(anyLong());
    }
}