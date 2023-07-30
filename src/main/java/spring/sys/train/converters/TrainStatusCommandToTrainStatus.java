package spring.sys.train.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.sys.train.commands.TrainStatusCommand;
import spring.sys.train.models.TrainStatus;
@Component
public class TrainStatusCommandToTrainStatus implements Converter<TrainStatusCommand, TrainStatus> {
    @Override
    @Synchronized
    @Nullable
    public TrainStatus convert(TrainStatusCommand source) {
        if(source == null) {
            return null;
        }
        final TrainStatus trainStatus=new TrainStatus();
        trainStatus.setStatusId(source.getStatusId());
        trainStatus.setDeparture(source.getDeparture());
        trainStatus.setLastUpdate(source.getLastUpdate());
        trainStatus.setAvailableSeats(source.getAvailableSeats());
        return trainStatus;
    }
}
