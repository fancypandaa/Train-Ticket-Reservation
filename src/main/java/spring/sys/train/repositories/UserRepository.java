package spring.sys.train.repositories;

import org.springframework.data.repository.CrudRepository;
import spring.sys.train.models.User;

public interface UserRepository extends CrudRepository<User,Long> {
}
