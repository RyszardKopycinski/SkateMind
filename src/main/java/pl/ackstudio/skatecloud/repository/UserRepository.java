package pl.ackstudio.skatecloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ackstudio.skatecloud.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
