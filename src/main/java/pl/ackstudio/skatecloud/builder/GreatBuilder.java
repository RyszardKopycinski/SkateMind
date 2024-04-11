package pl.ackstudio.skatecloud.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.ackstudio.skatecloud._development.ProgressNotesRepository;

@Component
public class GreatBuilder {

    private NavbarBuilder           navbarBuilder;
    private FooterBuilder           footerBuilder;
    private Model                   model;
    //tests
    private ProgressNotesRepository progressNotesRepository;

    @Autowired
    public GreatBuilder(FooterBuilder footerBuilder, NavbarBuilder navbarBuilder, ProgressNotesRepository progressNotesRepository) {
        this.footerBuilder = footerBuilder;
        this.navbarBuilder = navbarBuilder;
        this.progressNotesRepository = progressNotesRepository;
    }

    public GreatBuilder init(Model model) {
        this.model = model;
        return this;
    }

    public GreatBuilder addFooterContent() {
        this.model = footerBuilder.addUsers(model);
        return this;
    }

    public GreatBuilder addNavbarContent() {
        this.model = navbarBuilder.addLogedInfo(model);
        return this;
    }

    public Model build() {
        progressNotesRepository.findAll()
                               .forEach(element -> System.out.println(element));
        return model;
    }
}
