package pl.ackstudio.skatecloud.dataInput;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        System.out.println(firstFieldName);
        secondFieldName = constraintAnnotation.second();
        System.out.println(secondFieldName);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            final Field firstField = value.getClass()
                                          .getDeclaredField(firstFieldName);
            firstField.setAccessible(true);
            final Field secondField = value.getClass()
                                           .getDeclaredField(secondFieldName);
            secondField.setAccessible(true);
            final String firstValue = (String) firstField.get(value);
            final String secondValue = (String) secondField.get(value);
            return firstValue != null && firstValue.equals(secondValue);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
