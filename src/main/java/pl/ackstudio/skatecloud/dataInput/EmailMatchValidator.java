package pl.ackstudio.skatecloud.dataInput;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class EmailMatchValidator implements ConstraintValidator<EmailMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;

    @Override
    public void initialize(EmailMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.first();
        System.out.println(firstFieldName);
        secondFieldName = constraintAnnotation.second();
        System.out.println(secondFieldName);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            System.out.println("isValid 0..." + value);
            final Field firstField = value.getClass()
                                          .getDeclaredField(firstFieldName);
            System.out.println("isValid 1..." + firstField);
            firstField.setAccessible(true);
            System.out.println("isValid 2...");
            final Field secondField = value.getClass()
                                           .getDeclaredField(secondFieldName);
            System.out.println("isValid 3..." + secondField);
            secondField.setAccessible(true);
            System.out.println("isValid 4...");
            final String firstValue = (String) firstField.get(value);
            System.out.println(firstValue);
            final String secondValue = (String) secondField.get(value);
            System.out.println(secondValue);
            return firstValue != null && secondValue != null && firstValue.equals(secondValue);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
