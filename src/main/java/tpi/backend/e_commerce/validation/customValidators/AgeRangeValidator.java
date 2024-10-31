package tpi.backend.e_commerce.validation.customValidators;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AgeRangeValidator implements ConstraintValidator<AgeRange, Date> {
    private int min;
    private int max;

    @Override
    public void initialize(AgeRange constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Date dateBirth, ConstraintValidatorContext context) {
        if (dateBirth == null) {
            return true; // Permite campos nulos si no se especifica lo contrario
        }
        
        // Convertir Date a LocalDate
        LocalDate birthDate = dateBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        
        return age >= min && age <= max;
    }
}
