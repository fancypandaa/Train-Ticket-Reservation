package spring.sys.train.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
@Entity
@Getter
@Setter
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String status;
    private Long price;
    private String Type;
    private Date date;
    @ManyToOne
    private User user;
    @ManyToOne
    private Train train;
}
