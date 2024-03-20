package pl.ackstudio.skatecloud.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ackstudio.skatecloud.domain.UserPost;

import java.util.List;

public interface PostRepository extends CrudRepository<UserPost, Long> {

    //public List<UserPost> findAllByUser();
}
