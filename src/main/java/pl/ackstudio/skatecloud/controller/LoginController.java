package pl.ackstudio.skatecloud.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ackstudio.skatecloud.builder.GreatBuilder;
import pl.ackstudio.skatecloud.repository.UserRepository;

@Controller
@RequestMapping("/login")
public class LoginController {

    private GreatBuilder greatBuilder;

    @Autowired
    public LoginController(GreatBuilder greatBuilder) {
        this.greatBuilder = greatBuilder;
    }

    @GetMapping
    public String registerForm(Model model) {
        model = greatBuilder.init(model)
                            .addFooterContent()
                            .addLeftColumnContent()
                            .build();
        return "login.html";
    }

    /*@GetMapping
    public String login(@Valid @ModelAttribute("loginCheck") ) {
        return "home";
    }*/
}
