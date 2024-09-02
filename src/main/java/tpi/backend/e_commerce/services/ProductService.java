package tpi.backend.e_commerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.repositories.IProductRepository;

@Service
public class ProductService implements IProductService {
    
    @Autowired
    private IProductRepository productRepository;

    
    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAllActive(); 
        //Trae todos los productos activos
    }

    @Override
    public List<Product> findAllDeleted() {
        return (List<Product>) productRepository.findAllDeleted(); 
        //Trae todos los productos que han sido eliminados
    }

    @Override
    public Optional<Product> findById(Long id) { //Trae el producto por id ya sea que este activo o eliminado
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findActiveById(Long id) { //Trata a los productos eliminados como si no existieran
        return productRepository.findActiveById(id);

    }

    @Override
    public Optional<Product> findDeletedById(Long id) { //Trae por id solo si el producto esta eliminado
        return productRepository.findDeletedById(id);

    }

    @Override
    public void delete(Product product) { //Borrado logico
        product.setDeleted(true);
        productRepository.save(product);
        
    }

}
