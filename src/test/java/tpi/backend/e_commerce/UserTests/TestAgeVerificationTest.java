package tpi.backend.e_commerce.UserTests;

import java.util.Calendar;
import java.util.Date;
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
        // Fecha de nacimiento válida (dentro del rango de 18 a 100 años)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -25); // Ajustar a 25 años de edad
        Date validBirthDate = calendar.getTime();

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
        // Fecha de nacimiento menor de 18 años
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -15); // Ajustar a 15 años de edad
        Date underageBirthDate = calendar.getTime();

        SignUpRequest request = SignUpRequest.builder()
            .firstName("John")
            .lastName("Vanzetti")
            .dateBirth(underageBirthDate)
            .email("test@example.com")
            .password("securePassword123")
            .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Debe haber violaciones de validación para una edad menor a 18 años");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La edad debe estar entre 18 y 100 años")));
    }

    @Test
    void testInvalidAgeOver100() {
        // Fecha de nacimiento mayor a 100 años
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -105); // Ajustar a 105 años de edad
        Date overageBirthDate = calendar.getTime();

        SignUpRequest request = SignUpRequest.builder()
            .firstName("John")
            .lastName("Vanzetti")
            .dateBirth(overageBirthDate)
            .email("test@example.com")
            .password("securePassword123")
            .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Debe haber violaciones de validación para una edad mayor a 100 años");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La edad debe estar entre 18 y 100 años")));
    }
}
