package spring.sys.train.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Date departed;
    private Date arrived;
//    private String station;
    @ManyToOne
    private Train train;
    public Schedule(){

    }
    public Schedule(Date departed, Date arrived) {
        this.departed = departed;
        this.arrived = arrived;
    }
}
