package spring.sys.train.services;

import spring.sys.train.commands.TrainCommand;
import spring.sys.train.models.Train;
import java.util.*;
public interface TrainService {
    Set<Train> listTrains();
    TrainCommand findTrainByCurrentCityAndDestinationCity(String currentCity,String destinationCity);
    TrainCommand findTrainByDepartStation(String departStation);
    TrainCommand findTrainByArrivalStation(String arriveStation);
    Train findTrainById(Long Id);
}
