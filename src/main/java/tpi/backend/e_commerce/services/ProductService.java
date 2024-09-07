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
        return (List<Product>) productRepository.findAllActive(); //Trae todos los productos activos
    }

    @Override
    public List<Product> findAllDeleted() {
        return (List<Product>) productRepository.findAllDeleted(); //Trae todos los productos que han sido eliminados
    }

    @Override
    public Optional<Product> findById(Long id) { //Trata a los productos eliminados como si no existieran
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get(); 
            if (product.isDeleted()){
                return Optional.empty(); //De existir el producto pero estar eliminado, retorna un Optional vacio
            }
        }
        return optionalProduct; 
        /*
        De existir el producto y estar activo retorna un Optional de producto
        De no existir el producto retorna un optional vacio
        */

    }

    @Override
    public void delete(Product product) { //Borrado logico
        product.setDeleted(true);
        productRepository.save(product);
        
    }
    /* 
    Este metodo esta en revision
    @Override
    public Product recoverProduct(Long id){ //Permite recuperar un producto que fue eliminado a traves de su id
        productRepository.findById(id).ifPresentOrElse(p -> { //De encontrar el producto, ejecuta esta funcion de flecha, que recibe el producto
            p.setDeleted(false); //Setea la flag deleted en falso
        }, ()-> { //De no existir un producto con ese id, lanza una
            
        });
    }
    */

}
