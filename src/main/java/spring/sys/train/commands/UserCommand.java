package spring.sys.train.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.sys.train.models.Ticket;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand {
    private Long Id;
    private String userName;
    private String email;
    private String password;
    private String phone;
    private Set<Ticket> tickets= new HashSet<>();
}
