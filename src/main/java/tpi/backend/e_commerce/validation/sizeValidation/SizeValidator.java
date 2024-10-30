package tpi.backend.e_commerce.validation.sizeValidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SizeValidator implements ConstraintValidator<SizeValidation, String>{

    private int min;
    private int max;

    @Override
    public void initialize(SizeValidation constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;  // No validar valores nulos
        }
        return value.trim().length() >= min && value.trim().length() <= max;
    }
    
}
