package pl.ackstudio.skatecloud.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import pl.ackstudio.skatecloud.domain.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class GreatBuilder {

    private LeftColumnBuilder leftColumnBuilder;
    private FooterBuilder     footerBuilder;
    private Model             model;

    @Autowired
    public GreatBuilder(FooterBuilder footerBuilder, LeftColumnBuilder leftColumnBuilder) {
        this.footerBuilder = footerBuilder;
        this.leftColumnBuilder = leftColumnBuilder;
    }

    public GreatBuilder init(Model model) {
        this.model = model;
        return this;
    }

    public GreatBuilder addFooterContent() {
        this.model = footerBuilder.addUsers(model);
        return this;
    }

    public GreatBuilder addLeftColumnContent() {
        this.model = leftColumnBuilder.addTime(model);
        return this;
    }

    public Model build() {
        return model;
    }
}
