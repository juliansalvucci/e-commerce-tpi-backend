package tpi.backend.e_commerce.orderTests.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import tpi.backend.e_commerce.models.Order;


@ExtendWith(MockitoExtension.class)
public class Test10OrderDiscount {
    
    @InjectMocks
    private Order order;
    

    @BeforeEach
    void setUp(){
        order = new Order();
    }

    //Compra de 999.999
    @Test
    void testOrderWithoutDiscount(){
        order.setTotal(999999);
        order.calculateDiscount();
        assertEquals("0%", order.getDiscount());
        assertEquals(999999, order.getTotal());
    }    

    //Compra de 1.000.000
    @Test
    void testOrderWithoutDiscount2(){
        order.setTotal(1000000);
        order.calculateDiscount();
        assertEquals("0%", order.getDiscount());
        assertEquals(1000000, order.getTotal());
    }    

    //Compra de 1.000.001
    @Test
    void testOrderWithDiscount(){
        order.setTotal(1000001);
        order.calculateDiscount();
        assertEquals("5%", order.getDiscount());
        assertEquals(1000001.0*0.95, order.getTotal());
    }    
}
