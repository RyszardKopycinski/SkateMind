package pl.ackstudio.skatecloud._development;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.ackstudio.skatecloud.domain.State;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.domain.UserDetail;
import pl.ackstudio.skatecloud.repository.UserDetailRepository;
import pl.ackstudio.skatecloud.repository.UserRepository;

import java.time.LocalDate;

import static pl.ackstudio.skatecloud.domain.StatusInfo.*;

@Profile("dev")
@Configuration
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner loadTestData(UserRepository userRepo, UserDetailRepository userDetailRepo, PasswordEncoder passwordEncoder, ProgressNotesRepository progressNotesRepo) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws
                                            Exception {
                loadSimpleUsers();
                loadProgressNotes();
            }

            private void loadSimpleUsers() {
                userRepo.save(new User("Admin", "admin@ackstudio.pl", passwordEncoder.encode("admin"), "ROLE_ADMIN"));
                userRepo.save(new User("Klara", "klara@ackstudio.pl", passwordEncoder.encode("klara"), "ROLE_USER"));
                userRepo.save(new User("Gustaw", "gustaw@ackstudio.pl", passwordEncoder.encode("gustaw"), "ROLE_USER"));
                //Klara
                UserDetail klaraDetail = new UserDetail(userRepo.findByUsername("Klara"));
                klaraDetail.setFullname("Klara Klarita");
                klaraDetail.setAddress("Pałac Klary 1");
                klaraDetail.setCity("Wieliszew");
                klaraDetail.setState(State.MAZOW);
                klaraDetail.setZip("01-001");
                klaraDetail.setBirthDate(LocalDate.of(2000, 01, 01));
                klaraDetail.setPhoneNumber(null);
                userDetailRepo.save(klaraDetail);
                //Admin
                UserDetail adminDetail = new UserDetail(userRepo.findByUsername("Admin"));
                adminDetail.setFullname("Secret Admin");
                adminDetail.setAddress("Internet 127.0.0.0");
                adminDetail.setCity("Floppy disc");
                adminDetail.setState(State.MAZOW);
                adminDetail.setZip("00-000");
                adminDetail.setBirthDate(LocalDate.of(2002, 02, 20));
                adminDetail.setPhoneNumber("123456789");
                userDetailRepo.save(adminDetail);
            }

            private void loadProgressNotes() {
                progressNotesRepo.save(new ProgressNote(LocalDate.of(2024, 04, 11), "TO-FIX: Sessions management", WAITING));
                progressNotesRepo.save(new ProgressNote(LocalDate.of(2024, 04, 11), "TO-DO: About page", COMPLETED));
                progressNotesRepo.save(new ProgressNote(LocalDate.of(2024, 04, 11), "TO-DO: Create-edit user detailed information", COMPLETED));
                progressNotesRepo.save(new ProgressNote(LocalDate.of(2024, 04, 11), "TO-FIX: Basic security configuration", COMPLETED));
                progressNotesRepo.save(new ProgressNote(LocalDate.of(2024, 04, 12), "TO-DO: RESTful user's posts controller and reactive web", IN_PROGRESS));
                progressNotesRepo.save(new ProgressNote(LocalDate.of(2024, 04, 16), "TO-DO: Reactive web design", WAITING));
                progressNotesRepo.save(new ProgressNote(LocalDate.of(2024, 04, 18), "TO-DO: RESTful web content", IN_PROGRESS));
            }
        };
    }
}
