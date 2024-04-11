package pl.ackstudio.skatecloud.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.domain.UserDetail;

public interface UserDetailRepository extends CrudRepository<UserDetail, Long> {

    public UserDetail findByUser(User user);
}
