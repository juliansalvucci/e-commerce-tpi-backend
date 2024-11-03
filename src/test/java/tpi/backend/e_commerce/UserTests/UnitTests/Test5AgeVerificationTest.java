package tpi.backend.e_commerce.UserTests.UnitTests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class Test5AgeVerificationTest {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidAgeRange26112005() {
        // Fecha de nacimiento válida (dentro del rango de 18 a 100 años) - 18 años
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date validBirthDate = new Date();

        try {
            // Convertir "26-11-2005" en un objeto Date
            validBirthDate = formato.parse("26-11-2005");
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
        // Fecha de nacimiento válida (dentro del rango de 18 a 100 años) - 18 años
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date validBirthDate = new Date();

        try {
            validBirthDate = formato.parse("02-07-2006");
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
    void testInvalidAge28012007() {
        // Fecha de nacimiento menor de 18 años - 17 años
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date underageBirthDate = new Date();

        try {
            underageBirthDate = formato.parse("28-01-2007");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(underageBirthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Debe ser mayor de 18 años");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La edad debe estar entre 18 y 100 años")));
    }

    @Test
    void testValidAgeRange30101925() {
        // Fecha de nacimiento válida (dentro del rango de 18 a 100 años) - 99 años
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date validBirthDate = new Date();

        try {
            validBirthDate = formato.parse("30-10-1925");
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
    void testValidAgeRange15081924() {
        // Fecha de nacimiento válida (dentro del rango de 18 a 100 años) - 100 años
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date validBirthDate = new Date();

        try {
            validBirthDate = formato.parse("15-08-1924");
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
    void testInvalidAge01111923() {
        // Fecha de nacimiento mayor a 100 años - 101 años
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date overageBirthDate = new Date();

        try {
            overageBirthDate = formato.parse("01-11-1923");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SignUpRequest request = SignUpRequest.builder()
                .firstName("John")
                .lastName("Vanzetti")
                .dateBirth(overageBirthDate)
                .email("test@example.com")
                .password("securePassword123")
                .build();

        Set<ConstraintViolation<SignUpRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Debe ser menor a 100 años");
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("La edad debe estar entre 18 y 100 años")));
    }
}
