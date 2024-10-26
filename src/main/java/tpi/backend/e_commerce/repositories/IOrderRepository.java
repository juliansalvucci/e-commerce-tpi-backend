package tpi.backend.e_commerce.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tpi.backend.e_commerce.models.Order;

public interface IOrderRepository extends CrudRepository<Order,Long> {
    
    List<Order> findOrdersByUserId(Long id);
}
