package spring.sys.train.services;

import spring.sys.train.commands.TrainCommand;
import spring.sys.train.models.Train;
import java.util.*;
public interface TrainService {
    Set<Train> listTrains();
    Set<TrainCommand> findTrainByCurrentCityAndDestinationCity(String currentCity,String destinationCity);
    Set<TrainCommand> findTrainByDepartStation(String departStation);
    Set<TrainCommand> findTrainByArrivalStation(String arriveStation);
    Train findTrainById(Long Id);
    TrainCommand findTrainByCommandId(Long Id);

}
