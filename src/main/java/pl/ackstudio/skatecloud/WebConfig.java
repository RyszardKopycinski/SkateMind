package pl.ackstudio.skatecloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.ackstudio.skatecloud.repository.UserRepository;
import pl.ackstudio.skatecloud.domain.User;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("home");
    }

    @Bean
    public CommandLineRunner loadTestData(UserRepository userRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws
                                            Exception {
                User admin = new User("Admin", "admin@ackstudio.pl", "admin123", "ROLE_ADMIN");
                //userRepo.save(new User("Admin", "admin@ackstudio.pl", "admin@ackstudio.pl", "admin123", "admin123", "ADMIN"));
                //userRepo.save(new User("Klara", "klara@ackstudio.pl", "klara@ackstudio.pl", "klara123", "klara123", "USER"));
                //userRepo.save(new User("Gustaw", "gustaw@ackstudio.pl", "gustaw@ackstudio.pl", "gustaw123", "gustaw123", "USER"));
                //User test = userRepo.findByUsername("Admin");
                //System.out.println(test);
                userRepo.save(admin);
            }
        };
    }

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
