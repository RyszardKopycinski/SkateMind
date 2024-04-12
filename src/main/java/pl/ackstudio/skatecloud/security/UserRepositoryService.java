package pl.ackstudio.skatecloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.repository.UserRepository;

@Service
public class UserRepositoryService implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    public UserRepositoryService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws
                                                           UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
    }
}
