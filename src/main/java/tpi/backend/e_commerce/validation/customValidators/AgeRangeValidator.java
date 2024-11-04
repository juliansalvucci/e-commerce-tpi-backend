package tpi.backend.e_commerce.validation.customValidators;

import java.time.LocalDate;
import java.time.Period;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeRangeValidator implements ConstraintValidator<AgeRange, LocalDate> {
    private int min;
    private int max;

    @Override
    public void initialize(AgeRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return true; // Permite campos nulos si no se especifica lo contrario
        }
        
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        
        return age >= min && age <= max;
    }
}
