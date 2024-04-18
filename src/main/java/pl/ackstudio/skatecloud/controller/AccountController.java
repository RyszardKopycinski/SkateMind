package pl.ackstudio.skatecloud.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import pl.ackstudio.skatecloud.builder.GreatBuilder;
import pl.ackstudio.skatecloud.dataInput.UserDetailForm;
import pl.ackstudio.skatecloud.domain.State;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.domain.UserDetail;
import pl.ackstudio.skatecloud.repository.UserDetailRepository;
import pl.ackstudio.skatecloud.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class AccountController {

    private final UserRepository       userRepository;
    private final UserDetailRepository userDetailRepository;
    private final GreatBuilder         greatBuilder;

    @Autowired
    public AccountController(UserRepository userRepository, UserDetailRepository userDetailRepository, GreatBuilder greatBuilder) {
        this.userRepository = userRepository;
        this.userDetailRepository = userDetailRepository;
        this.greatBuilder = greatBuilder;
    }

    @GetMapping("/info")
    public String myAccount(Model model) {
        greatBuilder.init(model)
                    .addFooterContent()
                    .addNavbarContent();
        User user = userRepository.findByUsername(SecurityContextHolder.getContext()
                                                                       .getAuthentication()
                                                                       .getName());
        model.addAttribute("user", user);
        UserDetail userDetail = userDetailRepository.findByUser(user);
        model.addAttribute("userDetail", userDetail);
        System.out.println(userDetail);
        model.addAttribute("activePage", "myAccountPage");
        return "userPage";
    }

    @GetMapping("/info/{requestName}")
    public String otherAccount(@PathVariable String requestName, Model model) {
        greatBuilder.init(model)
                    .addFooterContent()
                    .addNavbarContent();
        System.out.println(requestName);
        User user = userRepository.findByUsername(requestName);
        if (user == null) {
            return "redirect:/";
        }
        System.out.println(user);
        model.addAttribute("user", user);
        UserDetail userDetail = userDetailRepository.findByUser(user);
        model.addAttribute("userDetail", userDetail);
        System.out.println(userDetail);
        model.addAttribute("activePage", "accountPage");
        return "userPage";
    }

    @ModelAttribute("updateDetail")
    public UserDetailForm updateDetails() {
        return new UserDetailForm();
    }

    @GetMapping("/edit")
    public String editMyAccount(Model model) {
        greatBuilder.init(model)
                    .addFooterContent()
                    .addNavbarContent();
        User user = userRepository.findByUsername(SecurityContextHolder.getContext()
                                                                       .getAuthentication()
                                                                       .getName());
        model.addAttribute("user", user);
        UserDetail userDetail = userDetailRepository.findByUser(user);
        model.addAttribute("userDetail", userDetail);
        System.out.println("GET /edit : userDetail : " + userDetail);
        model.addAttribute("activePage", "myAccountPage");
        model.addAttribute("states", State.values());
        return "userPageEdit";
    }

    @PostMapping("/edit")
    public String editDetails(@Valid @ModelAttribute("updateDetail") UserDetailForm updateDetails, Errors errors) {
        if (errors.hasErrors()) {
            for (ObjectError err : errors.getAllErrors()) {
                System.out.println(err);
            }
            return "userPageEdit";
        }
        User user = userRepository.findByUsername(SecurityContextHolder.getContext()
                                                                       .getAuthentication()
                                                                       .getName());
        UserDetail old = userDetailRepository.findByUser(user);
        if (old == null) {
            userDetailRepository.save(updateDetails.toUserDetail(user));
        } else {
            userDetailRepository.save(updateDetails.updateUserDetail(old));
        }
        System.out.println("POST /edit : updateDetails : " + updateDetails);
        return "redirect:/user/info";
    }
    //TODO: GET edycja/dodanie informacji
    //TODO: PUT aktualizacja informacji
}
