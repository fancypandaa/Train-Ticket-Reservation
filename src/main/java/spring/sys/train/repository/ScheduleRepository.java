package spring.sys.train.repository;

import org.springframework.data.repository.CrudRepository;
import spring.sys.train.model.Schedule;

public interface ScheduleRepository extends CrudRepository<Schedule,Long> {
}
