package pl.ackstudio.skatecloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.ackstudio.skatecloud.domain.UserPost;

import java.util.List;

public interface PostRepository extends JpaRepository<UserPost, Long> {
}
