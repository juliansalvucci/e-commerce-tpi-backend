package tpi.backend.e_commerce.services.order.interfaces;

import org.springframework.http.ResponseEntity;

import tpi.backend.e_commerce.dto.orderDTO.CreateOrderDto;

public interface ISaveOrderService {
    
    ResponseEntity<?> create(CreateOrderDto orderDto);

}
