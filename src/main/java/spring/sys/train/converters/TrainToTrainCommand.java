package spring.sys.train.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.sys.train.commands.TrainCommand;
import spring.sys.train.models.Train;
@Component

public class TrainToTrainCommand implements Converter<Train, TrainCommand> {
    private final TrainStatusToTrainStatusCommand trainStatusConverter;
    private final ScheduleToScheduleCommand scheduleCommandConverter;
    private final TicketToTicketCommand ticketConverter;

    public TrainToTrainCommand(TrainStatusToTrainStatusCommand trainStatusConverter, ScheduleToScheduleCommand scheduleCommandConverter, TicketToTicketCommand ticketConverter) {
        this.trainStatusConverter = trainStatusConverter;
        this.scheduleCommandConverter = scheduleCommandConverter;
        this.ticketConverter = ticketConverter;
    }

    @Override
    @Nullable
    @Synchronized
    public TrainCommand convert(Train source) {
        if(source==null){
            return null;
        }
        final TrainCommand trainCommand= new TrainCommand();
        trainCommand.setId(source.getId());
        trainCommand.setType(source.getType());
        trainCommand.setCapacity(source.getCapacity());
        trainCommand.setArriveStation(source.getArriveStation());
        trainCommand.setDepartStation(source.getDepartStation());
        trainCommand.setCurrentCity(source.getCurrentCity());
        trainCommand.setDestinationCity(source.getDestinationCity());
        trainCommand.setTrainStatus(trainStatusConverter.convert(source.getTrainStatus()));
        if(source.getSchedules()!=null && source.getSchedules().size()>0){
            source.getSchedules().forEach(scheduleCommand -> {
                trainCommand.getSchedules().add(scheduleCommandConverter.convert(scheduleCommand));
            });
        }
        if(source.getTickets()!=null && source.getTickets().size()>0){
            source.getTickets().forEach(ticketCommand -> {
                trainCommand.getTickets().add(ticketConverter.convert(ticketCommand));
            });
        }
        return trainCommand;
    }
}
