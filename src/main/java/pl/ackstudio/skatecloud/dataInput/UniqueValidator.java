package pl.ackstudio.skatecloud.dataInput;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    private ApplicationContext applicationContext;
    private FieldValueExists   service;
    private String             fieldName;

    @Override
    public void initialize(Unique constraintAnnotation) {
        Class<? extends FieldValueExists> uclass = constraintAnnotation.service();
        this.fieldName = constraintAnnotation.fieldName();
        String serviceQualifier = constraintAnnotation.serviceQualifier();
        if (serviceQualifier.equals("")) {
            this.service = this.applicationContext.getBean(uclass);
        } else {
            this.service = this.applicationContext.getBean(serviceQualifier, uclass);
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return !this.service.fieldValueExists(value, this.fieldName);
    }
}
