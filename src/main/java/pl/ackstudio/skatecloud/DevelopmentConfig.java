package pl.ackstudio.skatecloud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.ackstudio.skatecloud._development.ProgressNote;
import pl.ackstudio.skatecloud._development.ProgressNotesRepository;
import pl.ackstudio.skatecloud.domain.State;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.domain.UserDetail;
import pl.ackstudio.skatecloud.repository.UserDetailRepository;
import pl.ackstudio.skatecloud.repository.UserRepository;

import java.time.LocalDate;

import static pl.ackstudio.skatecloud.domain.StatusInfo.IN_PROGRESS;
import static pl.ackstudio.skatecloud.domain.StatusInfo.WAITING;

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
                UserDetail klaraDetail = new UserDetail(userRepo.findByUsername("Klara"));
                klaraDetail.setFullname("Klara Klarita");
                klaraDetail.setAddress("Pałac Klary 1");
                klaraDetail.setCity("Wieliszew");
                klaraDetail.setState(State.MAZOW);
                klaraDetail.setZip("01-101");
                klaraDetail.setBirthDate(LocalDate.of(2000, 01, 01));
                userDetailRepo.save(klaraDetail);
            }

            private void loadProgressNotes() {
                progressNotesRepo.save(new ProgressNote(LocalDate.of(2024, 04, 11), "TO FIX: Display of logged in users: shows logged out uses", WAITING));
                progressNotesRepo.save(new ProgressNote(LocalDate.of(2024, 04, 11), "TO DO: Create About page", IN_PROGRESS));
                progressNotesRepo.save(new ProgressNote(LocalDate.of(2024, 04, 11),
                                                        "TO DO: Create possibility to edit/update user detailed information",
                                                        WAITING));
            }
        };
    }
}
