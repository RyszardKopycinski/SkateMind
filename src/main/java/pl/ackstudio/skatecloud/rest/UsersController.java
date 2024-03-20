package pl.ackstudio.skatecloud.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UsersController {

    UserRepository userRepo;

    @Autowired
    public UsersController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<User> footerUsers() {
        System.out.println("allusers");
        List<User> users = new ArrayList<>();
        userRepo.findAll()
                .forEach(user -> users.add(user));
        return users;
    }

}
