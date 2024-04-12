package pl.ackstudio.skatecloud.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class NavbarBuilder {

    @Autowired
    public NavbarBuilder() {
    }

    public Model addLogedInfo(Model model) {
        String logInOut = null;
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            logInOut = authentication.getName();
        }
        System.out.println(logInOut);
        return model.addAttribute("loginout", logInOut);
    }
}
