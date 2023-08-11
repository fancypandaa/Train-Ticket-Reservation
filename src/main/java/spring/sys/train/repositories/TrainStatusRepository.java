package spring.sys.train.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import spring.sys.train.models.TrainStatus;

public interface TrainStatusRepository extends JpaRepository<TrainStatus,Long> {
}
