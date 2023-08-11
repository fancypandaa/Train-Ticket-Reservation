package spring.sys.train.services.apiServices;
import spring.sys.train.api.v1.modelsDTO.TrainDTO;
import spring.sys.train.commands.TrainCommand;
import spring.sys.train.models.Train;

import java.util.*;
public interface TrainDTOService {
    Set<TrainDTO> getAllTrains();
    TrainDTO getTrainById(Long Id);
    Set<TrainDTO> findTrainByCurrentCityAndDestinationCity(String currentCity, String destinationCity);
    Set<TrainDTO> findTrainByDepartStation(String departStation);
    Set<TrainDTO> findTrainByArrivalStation(String arriveStation);
}
