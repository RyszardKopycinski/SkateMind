package pl.ackstudio.skatecloud.dataInput;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = {PasswordMatchValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PasswordMatch {

    String first();
    String second();
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
