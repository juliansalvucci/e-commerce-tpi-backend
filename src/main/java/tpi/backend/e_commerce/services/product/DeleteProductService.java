package tpi.backend.e_commerce.services.product;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.mapper.ProductMapper;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.services.product.interfaces.IDeleteProductService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class DeleteProductService implements IDeleteProductService{
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private Validation validation;
    
    @Override
    public ResponseEntity<?> delete(Long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return validation.validate(
                "id",
                "No existe un producto con ese id",
                404
            );
        }

        Product product = optionalProduct.get();
        if (product.getStock() > 0) {
           return validation.validate(
               "stock",
               "No se puede eliminar un producto cuyo stock no es 0",
               409
           );
        }

        product.setDeleted(true);
        productRepository.save(product);
        return ResponseEntity.noContent().build();
       
    }

    @Override
    public ResponseEntity<?> recover(Long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return validation.validate(
                "id",
                "No existe un producto con ese id",
                404
            );
        }

        Product product = optionalProduct.get();
        product.setDeleted(false);
        productRepository.save(product);
        return ResponseEntity.ok(ProductMapper.toDTO(product));
        /* 
        Si se ingresa el id de un producto que existe y esta activo,
        se tratara de la misma manera que si estuviera eliminado. Se setea el atributo
        deleted en false (Ya estaba en false porque esta activo)
        */
    }

}
