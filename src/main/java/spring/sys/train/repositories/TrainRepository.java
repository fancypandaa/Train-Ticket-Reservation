package spring.sys.train.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.sys.train.models.Train;
import java.util.Optional;
public interface TrainRepository extends CrudRepository<Train,Long> {
    Optional<Train> findById(Long Id);
}
