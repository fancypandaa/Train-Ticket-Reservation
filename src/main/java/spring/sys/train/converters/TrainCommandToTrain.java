package spring.sys.train.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.sys.train.commands.TrainCommand;
import spring.sys.train.commands.TrainStatusCommand;
import spring.sys.train.models.Train;
@Component
public class TrainCommandToTrain implements Converter<TrainCommand, Train> {
    private final TrainStatusCommandToTrainStatus trainStatusConverter;
    private final ScheduleCommandToSchedule scheduleCommandToSchedule;
    private final TicketCommandToTicket ticketCommandToTicket;

    public TrainCommandToTrain(TrainStatusCommandToTrainStatus trainStatusConverter, ScheduleCommandToSchedule scheduleCommandToSchedule, TicketCommandToTicket ticketCommandToTicket) {
        this.trainStatusConverter = trainStatusConverter;
        this.scheduleCommandToSchedule = scheduleCommandToSchedule;
        this.ticketCommandToTicket = ticketCommandToTicket;
    }

    @Override
    @Nullable
    @Synchronized
    public Train convert(TrainCommand source) {
        if(source==null){
            return null;
        }
        final Train train= new Train();
        train.setId(source.getId());
        train.setType(source.getType());
        train.setCapacity(source.getCapacity());
        train.setArriveStation(source.getArriveStation());
        train.setDepartStation(source.getDepartStation());
        train.setCurrentCity(source.getCurrentCity());
        train.setDestinationCity(source.getDestinationCity());
        train.setTrainStatus(trainStatusConverter.convert(source.getTrainStatus()));
        if(source.getSchedules()!=null && source.getSchedules().size()>0){
            source.getSchedules().forEach(scheduleCommand -> {
                train.getSchedules().add(scheduleCommandToSchedule.convert(scheduleCommand));
            });
        }
        if(source.getTickets()!=null && source.getTickets().size()>0){
            source.getTickets().forEach(ticketCommand -> {
                train.getTickets().add(ticketCommandToTicket.convert(ticketCommand));
            });
        }
        return train;
    }
}
