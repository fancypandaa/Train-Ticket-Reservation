package spring.sys.train.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.sys.train.models.TrainStatus;

public interface TrainStatusRepository extends CrudRepository<TrainStatus,Long> {
}
