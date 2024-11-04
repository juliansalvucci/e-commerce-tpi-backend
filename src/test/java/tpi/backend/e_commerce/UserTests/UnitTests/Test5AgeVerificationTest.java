package tpi.backend.e_commerce.UserTests.UnitTests;

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
public class Test5AgeVerificationTest {
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

        assertTrue(violations.isEmpty(), "acepta la fecha de nacimiento sin mostrar errores");
    }

    @Test
    void testValidAgeRange02072006() {
        LocalDate birthDate = LocalDate.now().minusYears(18); 
        //18 Años

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(birthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "acepta la fecha de nacimiento sin mostrar errores");
    }

    @Test
    void testInvalidAge28012007() {
        LocalDate birthDate = LocalDate.now().minusYears(17); 
        //17 Años

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(birthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Debe ser mayor de 18 años");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La edad debe estar entre 18 y 100 años")));
    }

    @Test
    void testValidAgeRange30101925() {
        LocalDate birthDate = LocalDate.now().minusYears(99); 
        //99 Años

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(birthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "acepta la fecha de nacimiento sin mostrar errores");
    }

    @Test
    void testValidAgeRange15081924() {
        LocalDate birthDate = LocalDate.now().minusYears(100); 
        //100 Años

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(birthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "acepta la fecha de nacimiento sin mostrar errores");
    }

    @Test
    void testInvalidAge01111923() {
        LocalDate birthDate = LocalDate.now().minusYears(101); 
        //101 Años

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(birthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Debe ser menor a 100 años");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La edad debe estar entre 18 y 100 años")));
    }
}
