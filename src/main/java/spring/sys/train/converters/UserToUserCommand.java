package spring.sys.train.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import spring.sys.train.commands.UserCommand;
import spring.sys.train.models.User;

public class UserToUserCommand implements Converter<User, UserCommand> {
    private final TicketToTicketCommand ticketToTicketCommand;

    public UserToUserCommand(TicketToTicketCommand ticketToTicketCommand) {
        this.ticketToTicketCommand = ticketToTicketCommand;
    }

    @Override
    @Nullable
    @Synchronized
    public UserCommand convert(User source) {
        if(source == null) {
            return null;
        }
        final UserCommand userCommand=new UserCommand();
        userCommand.setId(source.getId());
        userCommand.setUserName(source.getUsername());
        userCommand.setEmail(source.getEmail());
        userCommand.setPassword(source.getPassword());
        userCommand.setPhone(source.getPhone());
        if(source.getTickets()!=null && source.getTickets().size()>0){
            source.getTickets().forEach(ticket -> {
                userCommand.getTickets().add(ticketToTicketCommand.convert(ticket));
            });
        }
        return userCommand;
    }
}
