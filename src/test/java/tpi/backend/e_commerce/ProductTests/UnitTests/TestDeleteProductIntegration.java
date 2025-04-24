package tpi.backend.e_commerce.ProductTests.UnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.transaction.Transactional;
import tpi.backend.e_commerce.controllers.ProductController;

@SpringBootTest
@Transactional
public class TestDeleteProductIntegration {

    @Autowired
    private ProductController productController;

    @Test
    void testDeleteProductWhenStockIsNotZero() {
        Long productId = 10L;

        ResponseEntity<?> response = productController.delete(productId);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

        // Si el body es un Map, hacemos cast para verificar el contenido
        if (response.getBody() instanceof Map<?, ?> body) {
            assertEquals("No se puede eliminar un producto cuyo stock no es 0", body.get("stock"));
        } 
    }

    @Test
    void testDeleteProductWhenStockIsZero() {
        Long productId = 20L;

        ResponseEntity<?> response = productController.delete(productId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

/*
 * package tpi.backend.e_commerce.ProductTests.UnitTests;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals;
 * import org.junit.jupiter.api.Test;
 * import org.mockito.InjectMocks;
 * import org.mockito.Mock;
 * import static org.mockito.Mockito.doReturn;
 * import org.springframework.boot.test.context.SpringBootTest;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.http.ResponseEntity;
 * 
 * import tpi.backend.e_commerce.controllers.ProductController;
 * import tpi.backend.e_commerce.services.product.DeleteProductService;
 * 
 * @SpringBootTest
 * public class Test4DeleteProductWithStock {
 * 
 * @Mock
 * private DeleteProductService productService;
 * 
 * @InjectMocks
 * private ProductController productController;
 * 
 * @Test
 * void testDeleteProductWhenStockIsNotZero() {
 * Long productId = 20L;
 * 
 * // Configurar el mock para simular la respuesta del servicio cuando el stock
 * no
 * // es cero
 * ResponseEntity<?> conflictResponse =
 * ResponseEntity.status(HttpStatus.CONFLICT)
 * .body("No se puede eliminar un producto cuyo stock no es 0");
 * 
 * doReturn(conflictResponse).when(productService).delete(productId);
 * 
 * // Ejecutar el método del controlador directamente
 * ResponseEntity<?> response = productController.delete(productId);
 * 
 * // Verificar que el estado HTTP sea 409 Conflict y el mensaje de error sea el
 * // esperado
 * assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
 * assertEquals("No se puede eliminar un producto cuyo stock no es 0",
 * response.getBody());
 * }
 * 
 * @Test
 * void testDeleteProductWhenStockIsZero() {
 * Long productId = 10L;
 * 
 * ResponseEntity<?> oktResponse = ResponseEntity.status(HttpStatus.OK)
 * .body("Eliminación exitosa");
 * 
 * doReturn(oktResponse).when(productService).delete(productId);
 * 
 * ResponseEntity<?> response = productController.delete(productId);
 * 
 * assertEquals(HttpStatus.OK, response.getStatusCode());
 * assertEquals("Eliminación exitosa", response.getBody());
 * }
 * }
 */