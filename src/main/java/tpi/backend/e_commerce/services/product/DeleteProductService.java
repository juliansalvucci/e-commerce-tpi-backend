package tpi.backend.e_commerce.services.product;

import org.springframework.beans.factory.annotation.Autowired;

import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.services.product.interfaces.IDeleteProductService;

public class DeleteProductService implements IDeleteProductService{
    @Autowired
    private IProductRepository productRepository;

}
