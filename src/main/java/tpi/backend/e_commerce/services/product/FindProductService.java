package tpi.backend.e_commerce.services.product;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.dto.ProductDTO.ResponseProductDTO;
import tpi.backend.e_commerce.mapper.ProductMapper;

import tpi.backend.e_commerce.models.Product;

import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.services.product.interfaces.IFindProductService;

@Service
public class FindProductService implements IFindProductService {
    
    @Autowired
    private IProductRepository productRepository;


    @Override
    public List<ResponseProductDTO> findAllActive() {
        return ProductMapper.toDTOList((List<Product>) productRepository.findAllActive()); 

    }

    @Override
    public List<ResponseProductDTO> findAllDeleted() {
        return ProductMapper.toDTOList((List<Product>) productRepository.findAllDeleted()); 

    }

    @Override
    public ResponseEntity<?> findActiveById(Long id) { //Trata a los productos eliminados como si no existieran
        Optional<Product> optionalProduct = productRepository.findActiveById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(404).body("Error: El producto ingresado no existe");
        }
        return ResponseEntity.ok(ProductMapper.toDTO(optionalProduct.get()));

    }

    @Override
    public ResponseEntity<?> findDeletedById(Long id) { //Trae por id solo si el producto esta eliminado
        Optional<Product> optionalProduct = productRepository.findDeletedById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(404).body("Error: El producto ingresado no existe");
        }
        return ResponseEntity.ok(ProductMapper.toDTO(optionalProduct.get()));
    }

    @Override
    public ResponseEntity<?> findByName(String name) {
        Optional<Product> optionalProduct = productRepository.findByName(name);
        if(optionalProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(ProductMapper.toDTO(optionalProduct.get()));
    }

}
