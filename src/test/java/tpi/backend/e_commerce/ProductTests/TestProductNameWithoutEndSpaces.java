package tpi.backend.e_commerce.ProductTests;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpi.backend.e_commerce.models.Product;

public class TestProductNameWithoutEndSpaces {
    private Product product;

    @BeforeEach
    void setUp() {
        // Crear una instancia de Product con espacios al inicio y al final del nombre
        product = Product.builder()
                .name("   Product Name with Spaces   ")
                .description("  Product Description   ")
                .price(10.0)
                .stock(5L)
                .stockMin(1L)
                .imageURL("  http://example.com/image.jpg   ")
                .color("   Blue  ")
                .size("  L  ")
                .build();
    }

    @Test
    void testPreCreateTrimsName() {
        // Invocar el método preCreate para que se aplique el recorte
        product.preCreate();

        // Verificar que el nombre ha sido recortado correctamente
        assertEquals("Product Name with Spaces", product.getName());
        assertEquals("Product Description", product.getDescription());
        assertEquals("http://example.com/image.jpg", product.getImageURL());
        assertEquals("Blue", product.getColor());
        assertEquals("L", product.getSize());
    }

    @Test
    void testPreCreateSetsCreationDate() {
        // Invocar el método preCreate
        product.preCreate();

        // Verificar que la fecha de creación no sea nula y esté cerca del tiempo actual
        LocalDateTime now = LocalDateTime.now();
        assertEquals(now.getYear(), product.getCreationDatetime().getYear());
        assertEquals(now.getMonth(), product.getCreationDatetime().getMonth());
        assertEquals(now.getDayOfMonth(), product.getCreationDatetime().getDayOfMonth());
    }
}
