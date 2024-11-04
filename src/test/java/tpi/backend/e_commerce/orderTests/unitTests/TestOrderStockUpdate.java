package tpi.backend.e_commerce.orderTests.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.services.product.ModifyProductService;



/*Este test testeara la actualizacion de stock de los productos al registrar una orden
Se registrara una orden para un producto cuyo stock actual es 10. Se compraran 5 unidades de ese producto
y luego se verificara que el stock del producto sea de 5
*/
@ExtendWith(MockitoExtension.class)
public class TestOrderStockUpdate {
    
    @InjectMocks
    private ModifyProductService modifyProductService;
    
    Product product;
    @BeforeEach
    void setUp(){
        product = new Product();
        product.setStock(10L);
    }

    @Test
    void testStockDiscount(){
        product = modifyProductService.discountStock(product, 5);
        assertEquals(5L, product.getStock());
    }
}
