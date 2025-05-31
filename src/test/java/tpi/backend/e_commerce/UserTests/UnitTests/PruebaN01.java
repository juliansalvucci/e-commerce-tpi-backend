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
public class PruebaN01 {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidBirthDate_26_11_2006() {
        LocalDate birthDate = LocalDate.of(2006, 11, 26); // Tiene 18 años cumplidos (válido)

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(birthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "Acepta la fecha de nacimiento sin mostrar errores");
    }

    @Test
    void testValidBirthDate_02_01_2007() {
        LocalDate birthDate = LocalDate.of(2007, 1, 2); // Tiene 18 años cumplidos al 07/05/2025 (válido)

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(birthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "Acepta la fecha de nacimiento sin mostrar errores");
    }

    @Test
    void testInvalidBirthDate_16_06_2008() {
        LocalDate birthDate = LocalDate.of(2008, 6, 16); // Tiene menos de 18 años (inválido)

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(birthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "No acepta la fecha y muestra un error");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La edad debe estar entre 18 y 100 años")));
    }
}
