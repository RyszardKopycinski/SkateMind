package pl.ackstudio.skatecloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.ackstudio.skatecloud._development.ProgressNote;
import pl.ackstudio.skatecloud._development.ProgressNotesRepository;
import pl.ackstudio.skatecloud.builder.GreatBuilder;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/about")
public class AboutController {

    GreatBuilder            greatBuilder;
    ProgressNotesRepository progressNotesRepository;

    @Autowired
    public AboutController(GreatBuilder greatBuilder, ProgressNotesRepository progressNotesRepository) {
        this.greatBuilder = greatBuilder;
        this.progressNotesRepository = progressNotesRepository;
    }

    @GetMapping
    public String getAbout(Model model) {
        greatBuilder.init(model)
                    .addFooterContent()
                    .addNavbarContent();
        List<ProgressNote> progressNotes = new ArrayList<>();
        progressNotesRepository.findAll()
                               .forEach(note -> progressNotes.add(note));
        model.addAttribute("progressNotes", progressNotes);
        model.addAttribute("activePage", "aboutPage");
        return "aboutPage.html";
    }
}
