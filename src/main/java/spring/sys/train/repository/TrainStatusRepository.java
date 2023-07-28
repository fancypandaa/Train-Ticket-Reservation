package spring.sys.train.repository;

import org.springframework.data.repository.CrudRepository;
import spring.sys.train.model.TrainStatus;

public interface TrainStatusRepository extends CrudRepository<TrainStatus,Long> {
}
