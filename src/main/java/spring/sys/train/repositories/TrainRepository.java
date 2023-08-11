package spring.sys.train.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import spring.sys.train.models.Ticket;
import spring.sys.train.models.Train;
import java.util.*;
public interface TrainRepository extends JpaRepository<Train,Long> {
    Optional<Train> findById(Long Id);
    Set<Train> findTrainByArriveStation(String arriveStation);
    Set<Train> findTrainByDepartStation(String departStation);
    Set<Train> findTrainByCurrentCityAndDestinationCity(String currentCity, String destinationCity);

}
