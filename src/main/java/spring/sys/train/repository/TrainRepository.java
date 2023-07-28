package spring.sys.train.repository;

import org.springframework.data.repository.CrudRepository;
import spring.sys.train.model.Train;
import java.util.Optional;
public interface TrainRepository extends CrudRepository<Train,Long> {
    Optional<Train> findById(Long Id);
}
