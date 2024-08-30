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

    private RuntimeException notFoundException = new RuntimeException("product doesn't exists");
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
    public Product findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            if (product.isDeleted()){
                throw new RuntimeException("Product have been deleted"); //De encontrar el producto pero este estar eliminado, lanzara una excepcion
            }
            return product;
        }else{
            throw notFoundException; //De no encontrar el producto, lanzara una excepcion

        }
    }

    @Override
    public void deleteById(Long id) { //Borrado logico
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setDeleted(true); //Setea la flag en true, por lo que el producto no se mostrara
            productRepository.save(product);
        }  
        throw notFoundException;
    }

    @Override
    public void recoverProduct(Long id){ //Permite recuperar un producto que fue eliminado a traves de su id
        productRepository.findById(id).ifPresentOrElse(p -> { //De encontrar el producto, ejecuta esta funcion de flecha, que recibe el producto
            p.setDeleted(false); //Setea la flag deleted en falso
        }, ()-> { //De no existir un producto con ese id, lanza una excepcion
            throw notFoundException;
        });
    }

}
