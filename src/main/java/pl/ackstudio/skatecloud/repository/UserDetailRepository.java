package pl.ackstudio.skatecloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.domain.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    UserDetail findByUser(User user);
}
