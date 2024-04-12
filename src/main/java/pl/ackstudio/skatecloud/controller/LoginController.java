package pl.ackstudio.skatecloud.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ackstudio.skatecloud.builder.GreatBuilder;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final GreatBuilder greatBuilder;

    public LoginController(GreatBuilder greatBuilder) {
        this.greatBuilder = greatBuilder;
    }

    @GetMapping
    public String logInOutForm(Model model) {
        model = greatBuilder.init(model)
                            .addFooterContent()
                            .addNavbarContent()
                            .build();
        model.addAttribute("activePage", "loginPage");
        return "loginPage.html";
    }

    @GetMapping("/out")
    public String requestLogout(Model model, HttpServletRequest request) {
        model = greatBuilder.init(model)
                            .addFooterContent()
                            .addNavbarContent()
                            .build();
        model.addAttribute("activePage", "logoutPage");
        //logoutHandler.logout(request, );
        return "loginPage.html";
    }
}
