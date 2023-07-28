package spring.sys.train.repository;

import org.springframework.data.repository.CrudRepository;
import spring.sys.train.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
}
