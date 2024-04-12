package pl.ackstudio.skatecloud.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@Table(name = "USERS_POSTS")
public class UserPost {

    private static final long   serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private              Long   id;
    @ManyToOne
    private final        User   postOwner;
    private              Date   createdAt;
    private final        String content;

    @PrePersist
    private void createdAt() {
        createdAt = new Date();
    }
}


