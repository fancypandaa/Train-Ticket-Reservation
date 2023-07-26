package spring.sys.train.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;
@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String status;
    private Long price;
    private String departStation;
    private String arrivalStation;
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne
    private Train train;
}
