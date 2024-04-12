package pl.ackstudio.skatecloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ackstudio.skatecloud.repository.PostRepository;

@Controller
@RequestMapping("/myposts")
public class PostsController {

    private final PostRepository postRepo;

    @Autowired
    public PostsController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }
}
