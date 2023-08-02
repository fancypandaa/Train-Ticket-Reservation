package spring.sys.train.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.sys.train.converters.TicketCommandToTicket;
import spring.sys.train.converters.TicketToTicketCommand;
import spring.sys.train.converters.TrainCommandToTrain;
import spring.sys.train.converters.TrainToTrainCommand;
import spring.sys.train.models.Ticket;
import spring.sys.train.models.Train;
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
class TrainServiceImpTest {
    TrainServiceImp trainServiceImp;
    @Mock
    TrainCommandToTrain trainCommandToTrain;
    @Mock
    TrainToTrainCommand trainToTrainCommand;
    @Mock
    TrainRepository trainRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        trainServiceImp = new TrainServiceImp(trainCommandToTrain,trainToTrainCommand,trainRepository);
    }

    @Test
    void listTrains() {
        Train train=new Train();
        HashSet trainData=new HashSet();
        trainData.add(train);
        when(trainServiceImp.listTrains()).thenReturn(trainData);
        Set<Train> trains= trainServiceImp.listTrains();
        assertEquals(trains.size(),1);
        verify(trainRepository,times(1)).findAll();
        verify(trainRepository,never()).findById(anyLong());
    }

    @Test
    void findTrainById() {
        Train train=new Train();
        train.setId(1L);
        Optional<Train> trainOptional=Optional.of(train);
        when(trainRepository.findById(anyLong())).thenReturn(trainOptional);
        Train optionalTrain=trainServiceImp.findTrainById(1L);
        assertNotNull("Null train returned",optionalTrain);
        verify(trainRepository,times(1)).findById(anyLong());
        verify(trainRepository,never()).findAll();
    }

}