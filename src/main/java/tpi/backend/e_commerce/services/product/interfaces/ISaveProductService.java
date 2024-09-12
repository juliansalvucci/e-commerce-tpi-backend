package tpi.backend.e_commerce.services.product.interfaces;

import org.springframework.http.ResponseEntity;

import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;

public interface ISaveProductService {
    ResponseEntity<?> save(CreateProductDTO createProductDTO);
    ResponseEntity<?> update(Long id, CreateProductDTO createProductDTO);
}
