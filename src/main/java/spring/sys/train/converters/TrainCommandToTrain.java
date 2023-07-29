package spring.sys.train.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import spring.sys.train.commands.TrainCommand;
import spring.sys.train.commands.TrainStatusCommand;
import spring.sys.train.models.Train;
@Component
public class TrainCommandToTrain implements Converter<TrainCommand, Train> {
//    private final TrainStatusCommandToTrainStatus trainStatusConverter;


    @Override
    @Nullable
    public Train convert(TrainCommand source) {
        if(source==null){
            return null;
        }
        final Train train= new Train();
        train.setId(source.getId());
    }
}
