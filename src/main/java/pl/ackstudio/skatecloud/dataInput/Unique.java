package pl.ackstudio.skatecloud.dataInput;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = {UniqueValidator.class})
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Unique {

    String message() default "{unique.value.violation}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    Class<? extends FieldValueExists> service();
    String serviceQualifier() default "";
    String fieldName();
}