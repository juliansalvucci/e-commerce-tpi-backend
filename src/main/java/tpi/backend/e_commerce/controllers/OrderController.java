package tpi.backend.e_commerce.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpi.backend.e_commerce.dto.orderDTO.CreateOrderDto;
import tpi.backend.e_commerce.services.order.interfaces.IFindOrderService;
import tpi.backend.e_commerce.services.order.interfaces.ISaveOrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class OrderController {
    
    @Autowired
    private ISaveOrderService saveOrderService;

    @Autowired
    private IFindOrderService findOrderService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateOrderDto orderDto, BindingResult result){
        return saveOrderService.create(orderDto, result);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findOrdersByUserId(@PathVariable Long id){
        return findOrderService.findOrdersByUserId(id);
    }
    
}
