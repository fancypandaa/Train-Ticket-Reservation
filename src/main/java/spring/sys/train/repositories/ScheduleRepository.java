package spring.sys.train.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import spring.sys.train.models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
