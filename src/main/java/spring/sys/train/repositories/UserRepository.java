package spring.sys.train.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import spring.sys.train.models.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
