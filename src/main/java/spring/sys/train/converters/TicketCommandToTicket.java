package spring.sys.train.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.models.Ticket;
import spring.sys.train.models.Train;
import spring.sys.train.models.User;

@Component
public class TicketCommandToTicket implements Converter<TicketCommand, Ticket> {
    @Override
    @Nullable
    @Synchronized
    public Ticket convert(TicketCommand source) {
        if (source == null) {
            return null;
        }
        final Ticket ticket=new Ticket();
        ticket.setId(source.getId());
        if(source.getTrainId() !=null){
            Train train= new Train();
            train.setId(source.getTrainId());
            ticket.setTrain(train);
            train.addTickets(ticket);
        }
        if(source.getUserId()!=null){
            User user= new User();
            user.setId(source.getUserId());
            ticket.setUser(user);
            user.addTickets(ticket);
        }
        ticket.setPrice(source.getPrice());
        ticket.setStatus(source.getStatus());
        ticket.setType(source.getType());
        ticket.setDate(source.getDate());
        return ticket;
    }
}
