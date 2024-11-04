package tpi.backend.e_commerce.UserTests;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;

@SpringBootTest
public class TestAgeVerificationTest {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidAgeRange() {

        LocalDate validBirthDate = LocalDate.now().minusYears(25); 
        //Fecha de nacimiento de ejemplo de 25 años de edad

        SignUpRequest request = SignUpRequest.builder()
            .firstName("John")
            .lastName("Vanzetti")
            .dateBirth(validBirthDate)
            .email("test@example.com")
            .password("securePassword123")
            .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "No debería haber violaciones de validación para una edad válida");
    }

    @Test
    void testInvalidAgeUnder18() {

        LocalDate teenBirthDate = LocalDate.now().minusYears(15); 

        SignUpRequest request = SignUpRequest.builder()
            .firstName("John")
            .lastName("Vanzetti")
            .dateBirth(teenBirthDate)
            .email("test@example.com")
            .password("securePassword123")
            .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Debe haber violaciones de validación para una edad menor a 18 años");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La edad debe estar entre 18 y 100 años")));
    }

    @Test
    void testInvalidAgeOver100() {
       
        LocalDate oldBirthDate = LocalDate.now().minusYears(105); 

        SignUpRequest request = SignUpRequest.builder()
            .firstName("John")
            .lastName("Vanzetti")
            .dateBirth(oldBirthDate)
            .email("test@example.com")
            .password("securePassword123")
            .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Debe haber violaciones de validación para una edad mayor a 100 años");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La edad debe estar entre 18 y 100 años")));
    }
}
