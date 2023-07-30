package spring.sys.train.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.sys.train.commands.UserCommand;
import spring.sys.train.models.User;
@Component
public class UserCommandToUser implements Converter<UserCommand, User> {
    private final TicketCommandToTicket ticketCommandToTicket;

    public UserCommandToUser(TicketCommandToTicket ticketCommandToTicket) {
        this.ticketCommandToTicket = ticketCommandToTicket;
    }

    @Override
    @Nullable
    @Synchronized
    public User convert(UserCommand source) {
        if(source == null) {
            return null;
        }
        final User user=new User();
        user.setId(source.getId());
        user.setUserName(source.getUserName());
        user.setEmail(source.getEmail());
        user.setPassword(source.getPassword());
        user.setPhone(source.getPhone());
        if(source.getTickets()!=null && source.getTickets().size()>0){
            source.getTickets().forEach(ticket -> {
                user.getTickets().add(ticketCommandToTicket.convert(ticket));
            });
        }
        return user;
    }
}
