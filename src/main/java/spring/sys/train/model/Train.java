package spring.sys.train.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.*;

@Entity
@Getter
@Setter
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String departStation;
    private String arriveStation;
    private String type;
    private Long capacity;
    private String currentCity;
    private String destinationCity;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "train")
    private Set<Ticket> tickets= new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    private TrainStatus trainStatus;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "train")
    private Set<Schedule> schedules=new HashSet<>();

    public void setTrainStatus(TrainStatus status) {
        if (status != null) {
            this.trainStatus = status;
            status.setTrain(this);
        }
    }
    public Train addSchedule(Schedule schedule){
        schedule.setTrain(this);
        this.schedules.add(schedule);
        return this;
    }
    public Train addTickets(Ticket ticket){
        ticket.setTrain(this);
        this.tickets.add(ticket);
        return this;
    }
}
