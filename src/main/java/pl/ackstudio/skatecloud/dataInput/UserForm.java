package pl.ackstudio.skatecloud.dataInput;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.ackstudio.skatecloud.domain.User;

@Data
@EmailMatch(first = "email", second = "confirmEmail", message = "różne emaile")
@PasswordMatch(first = "password", second = "confirmPassword", message = "różne hasła")
public class UserForm {

    @NotEmpty(message = "pole obowiązkowe")
    @Size(min = 3, message = "nazwa użytkownika musi zawierać minimum 5 znaków")
    @Pattern(regexp = "^[a-zA-Z]\\w+", message = "nazwa użytkownika może zawierać tylko litery, cyfry, znak \'_\' oraz zaczynać się od litery")
    private String username;
    @NotEmpty(message = "pole obowiązkowe")
    @Email(message = "nie rozpoznano prawidłowego adresu email")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "pole obowiązkowe")
    @Email(message = "nie rozpoznano prawidłowego adresu email")
    private String confirmEmail;
    @NotEmpty(message = "pole obowiązkowe")
    @Size(min = 3, message = "hasło musi zawierać minimalnie 6 znaków")
    @Pattern(regexp = "(\\w|[!@#$%^&*=+])+", message = "hasło może składać się tylko z liter, cyft oraz znaków: !@#$%^&*=+")
    private String password;
    @NotEmpty(message = "pole obowiązkowe")
    private String confirmPassword;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, email, passwordEncoder.encode(password), "ROLE_USER");
    }
}

