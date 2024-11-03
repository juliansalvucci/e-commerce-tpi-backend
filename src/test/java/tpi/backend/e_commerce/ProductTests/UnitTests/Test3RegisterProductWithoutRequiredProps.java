package tpi.backend.e_commerce.ProductTests.UnitTests;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;

@SpringBootTest
public class Test3RegisterProductWithoutRequiredProps {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateProductWithoutOptionalFields() {
        // Crear un objeto CreateProductDTO sin los campos opcionales
        CreateProductDTO productDTO = new CreateProductDTO(
            "Producto de prueba",     // name
            "Descripción de prueba",   // description
            100.0,                     // price
            10L,                       // stock
            1L,                        // stockMin
            null,                      // imageURL (opcional)
            "Rojo",                    // color
            null,                      // size (opcional)
            1L,                        // brandId
            1L                         // subCategoryId
        );

        // Validar el objeto y obtener las violaciones de las restricciones
        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(productDTO);

        // Verificar que no hay violaciones
        assertTrue(violations.isEmpty(), "El producto debería ser válido sin campos opcionales");
    }

    @Test
    void testCreateProductWithoutSize() {
        // Crear un objeto CreateProductDTO sin los campos opcionales
        CreateProductDTO productDTO = new CreateProductDTO(
            "Producto de prueba",     // name
            "Descripción de prueba",   // description
            100.0,                     // price
            10L,                       // stock
            1L,                        // stockMin
            "http://example.com/image.jpg",                      // imageURL (opcional)
            "Rojo",                    // color
            null,                      // size (opcional)
            1L,                        // brandId
            1L                         // subCategoryId
        );

        // Validar el objeto y obtener las violaciones de las restricciones
        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(productDTO);

        // Verificar que no hay violaciones
        assertTrue(violations.isEmpty(), "El producto debería ser válido sin campos opcionales");
    }

    @Test
    void testCreateProductWithoutImageURL() {
        // Crear un objeto CreateProductDTO sin los campos opcionales
        CreateProductDTO productDTO = new CreateProductDTO(
            "Producto de prueba",     // name
            "Descripción de prueba",   // description
            100.0,                     // price
            10L,                       // stock
            1L,                        // stockMin
            null,                      // imageURL (opcional)
            "Rojo",                    // color
            "XXL",                      // size (opcional)
            1L,                        // brandId
            1L                         // subCategoryId
        );

        // Validar el objeto y obtener las violaciones de las restricciones
        Set<ConstraintViolation<CreateProductDTO>> violations = validator.validate(productDTO);

        // Verificar que no hay violaciones
        assertTrue(violations.isEmpty(), "El producto debería ser válido sin campos opcionales");
    }
}

