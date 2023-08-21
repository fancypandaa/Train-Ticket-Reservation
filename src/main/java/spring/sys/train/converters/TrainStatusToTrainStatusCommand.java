package spring.sys.train.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.sys.train.commands.TrainStatusCommand;
import spring.sys.train.models.TrainStatus;
@Component
public class TrainStatusToTrainStatusCommand implements Converter<TrainStatus, TrainStatusCommand> {
    @Override
    @Synchronized
    @Nullable
    public TrainStatusCommand convert(TrainStatus source) {
        if (source == null) {
            return null;
        }
        final TrainStatusCommand statusCommand=new TrainStatusCommand();
        statusCommand.setStatusId(source.getStatusId());
        statusCommand.setDeparture(source.getDeparture());
        statusCommand.setLastUpdate(source.getLastUpdate());
        statusCommand.setAvailableSeats(source.getAvailableSeats());
        return statusCommand;
    }
}
