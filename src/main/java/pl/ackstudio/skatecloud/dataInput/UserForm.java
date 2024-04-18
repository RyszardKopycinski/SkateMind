package pl.ackstudio.skatecloud.dataInput;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.ackstudio.skatecloud.domain.User;

@Data
@EmailMatch(first = "email", second = "confirmEmail", message = "e-mails are different")
@PasswordMatch(first = "password", second = "confirmPassword", message = "passwords don't match")
public class UserForm {

    @NotEmpty(message = "field required")
    @Size(min = 5, message = "must contain at least 5 characters")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "username must consist only of letters and numbers")
    private String username;
    @NotEmpty(message = "field required")
    @Email(message = "valid e-mail not recognized")
    @Column(unique = true)
    private String email;
    @NotEmpty(message = "field required")
    @Email(message = "valid e-mail not recognized")
    private String confirmEmail;
    @NotEmpty(message = "field required")
    @Size(min = 5, message = "must contain at least 5 characters")
    @Pattern(regexp = "(\\w|[_!@#$%^&*=+])+", message = "password must consist only letters, numbers or characters: _!@#$%^&*=+")
    private String password;
    @NotEmpty(message = "field required")
    private String confirmPassword;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, email, passwordEncoder.encode(password), "ROLE_USER");
    }
}

