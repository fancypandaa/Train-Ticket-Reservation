package spring.sys.train.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.sys.train.commands.TicketCommand;
import spring.sys.train.commands.TrainCommand;
import spring.sys.train.commands.UserCommand;
import spring.sys.train.models.Ticket;

@Component
public class TicketToTicketCommand implements Converter<Ticket, TicketCommand> {
    @Override
    @Nullable
    @Synchronized
    public TicketCommand convert(Ticket source) {
        if (source == null) {
            return null;
        }
        final TicketCommand ticketCommand=new TicketCommand();
        ticketCommand.setId(source.getId());
        if(source.getTrain() !=null){
            TrainCommand trainCommand= new TrainCommand();
            trainCommand.setId(source.getTrain().getId());
        }
        if(source.getUser()!=null){
            UserCommand userCommand= new UserCommand();
            userCommand.setId(source.getUser().getId());
        }
        ticketCommand.setPrice(source.getPrice());
        ticketCommand.setStatus(source.getStatus());
        ticketCommand.setType(source.getType());
        ticketCommand.setDate(source.getDate());
        return ticketCommand;
    }
}
