package tpi.backend.e_commerce.ProductTests.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import tpi.backend.e_commerce.models.Product;

@SpringBootTest
public class Test2ProductNameWithoutEndSpaces {
    private Product product;

    @BeforeEach
    void setUp() {
        // Inicialización general que puede usarse para diferentes pruebas
        product = Product.builder()
                .description("descripción")
                .price(10.0)
                .stock(5L)
                .stockMin(1L)
                .imageURL("http://example.com/image.jpg")
                .color("Azul")
                .size("M")
                .build();
    }

    @Test
    void testPreCreateTrimsNameSamsungGalaxyS22() {
        // Asignar el nombre con espacios al inicio y final
        product.setName("Samsung Galaxy S22  ");

        // Invocar el método preCreate para que se aplique el recorte
        product.preCreate();

        // Verificar que el nombre ha sido recortado correctamente
        assertEquals("Samsung Galaxy S22", product.getName());
    }

    @Test
    void testPreCreateTrimsNameSamsungGalaxyS24() {
        // Asignar el nombre sin espacios adicionales
        product.setName("Samsung Galaxy S24");

        // Invocar el método preCreate para verificar que no cambia un nombre sin espacios
        product.preCreate();

        // Verificar que el nombre permanece sin cambio
        assertEquals("Samsung Galaxy S24", product.getName());
    }

   
}
