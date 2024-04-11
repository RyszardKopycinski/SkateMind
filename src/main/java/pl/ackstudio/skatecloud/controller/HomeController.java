package pl.ackstudio.skatecloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ackstudio.skatecloud.builder.GreatBuilder;

@Controller
@RequestMapping("/")
public class HomeController {

    private GreatBuilder greatBuilder;

    @Autowired
    public HomeController(GreatBuilder greatBuilder) {
        this.greatBuilder = greatBuilder;
    }

    @GetMapping
    public String home(Model model) {
        model = greatBuilder.init(model)
                            .addFooterContent()
                            .addNavbarContent()
                            .build();
        model.addAttribute("activePage","homePage");
        return "homePage";
    }
}
