package pl.ackstudio.skatecloud.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Date;

@Component
public class LeftColumnBuilder {

    @Autowired
    public LeftColumnBuilder() {
    }

    public Model addTime(Model model) {
        return model.addAttribute("time", new Date());
    }
}
