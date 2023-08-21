package spring.sys.train.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Entity
@Getter
@Setter
public class TrainStatus {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long statusId;
    @OneToOne
    private Train train;
    private Date lastUpdate;
    private Long availableSeats;
    @Enumerated(value=EnumType.STRING)
    private Departure departure;
}
