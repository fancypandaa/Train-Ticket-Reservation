package spring.sys.train.converters;

import org.springframework.core.convert.converter.Converter;
import spring.sys.train.commands.TrainCommand;
import spring.sys.train.models.Train;

public class TrainToTrainCommand implements Converter<Train, TrainCommand> {
    @Override
    public TrainCommand convert(Train source) {
        return null;
    }
}
