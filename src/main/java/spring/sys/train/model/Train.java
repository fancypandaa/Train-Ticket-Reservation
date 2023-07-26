package spring.sys.train.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.*;

@Entity
@Data
public class Train {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String departStation;
    private String type;
    private String departTime;
    private Long capacity;
    private String bound;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "train")
    private Set<Ticket> tickets= new HashSet<>();

    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "train")
    private List<TrainStatus> trainStatus;


    @OneToMany(cascade= CascadeType.ALL,fetch=FetchType.LAZY,mappedBy = "train")
    private List<Schedule> schedules;
}
