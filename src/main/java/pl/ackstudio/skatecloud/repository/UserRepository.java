package pl.ackstudio.skatecloud.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ackstudio.skatecloud.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);
}
