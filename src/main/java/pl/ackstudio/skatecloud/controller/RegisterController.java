package pl.ackstudio.skatecloud.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ackstudio.skatecloud.builder.GreatBuilder;
import pl.ackstudio.skatecloud.dataInput.UserForm;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserRepository  userRepo;
    private final PasswordEncoder passwordEncoder;
    private final GreatBuilder    greatBuilder;

    @Autowired
    public RegisterController(UserRepository userRepo, PasswordEncoder passwordEncoder, GreatBuilder greatBuilder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.greatBuilder = greatBuilder;
    }

    @ModelAttribute("newUser")
    public UserForm newUser() {
        return new UserForm();
    }

    @GetMapping
    public String registerForm(Model model) {
        model = greatBuilder.init(model)
                            .addFooterContent()
                            .addNavbarContent()
                            .build();
        model.addAttribute("activePage", "registerPage");
        return "registrationPage";
    }

    @PostMapping
    public String newUser(@Valid @ModelAttribute("newUser") UserForm newUser, Errors errors) {
        if (errors.hasErrors()) {
            for (ObjectError err : errors.getAllErrors()) {
                System.out.println(err);
            }
            return "registrationPage";
        }
        User user = userRepo.save(newUser.toUser(passwordEncoder));
        System.out.println(user);
        System.out.println(userRepo.findByUsername(newUser.getUsername()));
        return "redirect:/login";
    }
}
