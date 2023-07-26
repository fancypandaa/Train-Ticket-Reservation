package spring.sys.train.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
public class Schedule {
    private Date time;
    private String Station;
    @ManyToOne(cascade= CascadeType.ALL)
    private Train train;
}
