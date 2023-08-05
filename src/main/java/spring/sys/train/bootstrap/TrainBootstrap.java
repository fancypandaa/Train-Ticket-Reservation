package spring.sys.train.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.sys.train.models.*;
import spring.sys.train.repositories.*;

import org.springframework.transaction.annotation.Transactional;
import java.util.*;
@Slf4j
@Component
public class TrainBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final TrainRepository trainRepository;
    private final TicketRepository ticketRepository;
    private final TrainStatusRepository trainStatusRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final PasswordEncoder passwordEncoder;

    public TrainBootstrap(TrainRepository trainRepository, TicketRepository ticketRepository, TrainStatusRepository trainStatusRepository, UserRepository userRepository,
                          ScheduleRepository scheduleRepository,PasswordEncoder passwordEncoder) {
        this.trainRepository = trainRepository;
        this.ticketRepository = ticketRepository;
        this.trainStatusRepository = trainStatusRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Loading Bootstrap Data");
//        trainRepository.saveAll(getTrains());
//        ticketRepository.saveAll(getTickets());
//        userRepository.saveAll(getUsers());
    }
    private List<Train> getTrains(){
        List<Train> trains= new ArrayList<>(2);
        Train tt=new Train();
        tt.setDepartStation("Moynihan Train Hall at Penn Sta.");
        tt.setArriveStation("Union Station");
        tt.setCurrentCity("New York, NY");
        tt.setDestinationCity("Washington, DC");
        tt.setType("67 Northeast Regional");
        tt.setCapacity(200L);
        TrainStatus trainStatus=new TrainStatus();
        trainStatus.setAvailableSeats(190L);
        trainStatus.setDeparture(Departure.ON_TIME);
        trainStatus.setLastUpdate(new Date());
        tt.setTrainStatus(trainStatus);
        tt.addSchedule(new Schedule(new Date(),new Date()));
        tt.addSchedule(new Schedule(new Date(),new Date()));
        trains.add(tt);

        Train tt_1=new Train();
        tt_1.setDepartStation("Penn Station");
        tt_1.setArriveStation("Moynihan Train Hall at Penn Sta.");
        tt_1.setCurrentCity(" Newark, NJ ");
        tt_1.setDestinationCity(" New York, NY ");
        tt_1.setType("67 Northeast Regional");
        tt_1.setCapacity(200L);
        TrainStatus trainStatuss=new TrainStatus();
        trainStatuss.setAvailableSeats(195L);
        trainStatuss.setDeparture(Departure.ON_TIME);
        trainStatuss.setLastUpdate(new Date());
        tt_1.setTrainStatus(trainStatuss);
        tt_1.addSchedule(new Schedule(new Date(),new Date()));
        tt_1.addSchedule(new Schedule(new Date(),new Date()));
        trains.add(tt_1);

        return trains;
    }

    private List<Ticket> getTickets(){
        List<Ticket> tickets = new ArrayList<>(2);
        Ticket ti = new Ticket();
        ti.setStatus("not paid");
        ti.setPrice(120L);
        ti.setType("Business");
        ti.setDate(new Date());
        Optional<Train> trainI = trainRepository.findById(31L);
        ti.setTrain(trainI.get());

        tickets.add(ti);
        return tickets;
    }

    private List<User> getUsers(){
        List<User> users = new ArrayList<>(2);
        User user = new User();
        user.setEmail("lol@mail.com");
        user.setUserName("lol");
        user.setPhone("666-333-444");
        user.setPassword(passwordEncoder.encode("3333444Q"));
//        Optional<Ticket> ticket = ticketRepository.findById(16L);
//        user.addTickets(ticket.get());

        users.add(user);
        return users;
    }
}
