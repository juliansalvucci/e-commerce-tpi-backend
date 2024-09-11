package tpi.backend.e_commerce.services.product;

import org.springframework.beans.factory.annotation.Autowired;

import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.services.product.interfaces.ISaveProductService;

public class SaveProductService implements ISaveProductService{
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }
}
