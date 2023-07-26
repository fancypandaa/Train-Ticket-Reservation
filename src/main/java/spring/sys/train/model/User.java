package spring.sys.train.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String userName;
    private String email;

    private String password;
    private String phone;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<Ticket> tickets= new HashSet<>();

}
