package pl.ackstudio.skatecloud.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class FooterBuilder {

    private UserRepository userRepository;

    @Autowired
    public FooterBuilder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Model addUsers(Model model) {
        System.out.println(new Object() {
        }.getClass()
         .getEnclosingMethod()
         .getName());
        List<User> users = new ArrayList<>();
        userRepository.findAll()
                      .forEach(user -> users.add(user));
        System.out.println(users);
        return model.addAttribute("users", users);
    }
    //TODO: budowniczy stopki. Dodaje do modelu informacje zawarte w stopce
}
