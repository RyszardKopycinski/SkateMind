package pl.ackstudio.skatecloud.builder;

import jakarta.servlet.http.HttpSessionBindingListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FooterBuilder implements HttpSessionBindingListener {

    private UserRepository  userRepository;
    @Autowired
    private SessionRegistry sessionRegistry;

    @Autowired
    public FooterBuilder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Model addUsers(Model model) {
        List<User> users = new ArrayList<>();
        userRepository.findAll()
                      .forEach(users::add);
        model.addAttribute("users", users);
        //Currently loggeg users
        List<Object> principals = sessionRegistry.getAllPrincipals()
                                                 .stream()
                                                 .filter(u -> !sessionRegistry.getAllSessions(u, false)
                                                                              .isEmpty())
                                                 .collect(Collectors.toList());
        System.out.println("PRINCIPALS:");
        System.out.println(principals);
        List<String> loggedUsersNames = new ArrayList<String>();
        for (Object principal : principals) {
            if (principal instanceof User) {
                loggedUsersNames.add(((User) principal).getUsername());
            }
        }
        System.out.println("Logged users: " + loggedUsersNames);
        model.addAttribute("loggedUsers", loggedUsersNames);
        return model;
    }
    //TODO: budowniczy stopki. Dodaje do modelu informacje zawarte w stopce
}
