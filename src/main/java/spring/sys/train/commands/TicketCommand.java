package spring.sys.train.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.sys.train.models.Train;
import spring.sys.train.models.User;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TicketCommand {
    private Long Id;
    private String status;
    private Long price;
    private String Type;
    private Date date;
    private User user;
    private Train train;
}
