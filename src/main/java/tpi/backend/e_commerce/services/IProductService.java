package tpi.backend.e_commerce.services;

import java.util.List;
import java.util.Optional;

import tpi.backend.e_commerce.models.Product;

public interface IProductService {
    
    Product saveProduct(Product product);

    List<Product> findAll();

    List<Product> findAllDeleted();

    Optional<Product> findById(Long id);
    
    void delete(Product product); //Borrado logico

    //Product recoverProduct(Long id);
    

}   