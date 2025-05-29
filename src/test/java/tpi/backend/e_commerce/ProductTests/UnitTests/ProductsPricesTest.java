package tpi.backend.e_commerce.ProductTests.UnitTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validation;

import org.springframework.boot.test.context.SpringBootTest;
import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;

@SpringBootTest
class ProductsPricesTest {

    private static Validator validator;

    // Creamos un Validator que usaremos para validar los objetos CreateProductDTO según las anotaciones de validación
    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // Creamos una instancia válida de CreateProductDTO que usaremos como base para las pruebas
    private CreateProductDTO createValidDTO() {
        return new CreateProductDTO(
                "Product Name",
                "A valid description",
                100.0,
                10L,
                1L,
                "https://example.com/image.jpg",
                "Red",
                "M",
                1L,
                2L
        );
    }

    @Test
    void priceOne_shouldPassValidation() {
        CreateProductDTO dto = createValidDTO();
        dto.setPrice(1.0);
        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());
    }

    @Test
    void priceZero_shouldFailValidation() {
        CreateProductDTO dto = createValidDTO();
        dto.setPrice(0.0);
        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        // El error debe estar en el campo "price" y debe mostrar el mensaje personalizado
        assertTrue(violations.stream().anyMatch(v ->
                v.getPropertyPath().toString().equals("price") &&
                        v.getMessage().equals("El precio debe ser mayor o igual a 1")
        ));
    }

    @Test
    void priceNegativeOne_shouldFailValidation() {
        CreateProductDTO dto = createValidDTO();
        dto.setPrice(-1.0);
        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(dto);
        assertFalse(violations.isEmpty());

        // El error debe estar en el campo "price" y debe mostrar el mensaje personalizado
        assertTrue(violations.stream().anyMatch(v ->
                v.getPropertyPath().toString().equals("price") &&
                        v.getMessage().equals("El precio debe ser mayor o igual a 1")
        ));
    }
}
