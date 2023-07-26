package spring.sys.train.model;

import javax.persistence.*;
import java.util.Date;
@Entity
public class TrainStatus {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long statusId;

    @ManyToOne(cascade=CascadeType.ALL)
    private Train train;
    private Date availableDate;
    private Long availabelSeats;
}
