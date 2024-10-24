package tpi.backend.e_commerce.repositories;

import org.springframework.data.repository.CrudRepository;

import tpi.backend.e_commerce.models.Order;

public interface IOrderRepository extends CrudRepository<Order,Long> {
    
    
}
