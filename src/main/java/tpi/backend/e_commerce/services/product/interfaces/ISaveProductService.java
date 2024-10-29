package tpi.backend.e_commerce.services.product.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;
import tpi.backend.e_commerce.dto.ProductDTO.UpdateStockDTO;

public interface ISaveProductService {
    ResponseEntity<?> save(CreateProductDTO createProductDTO, BindingResult result);
    ResponseEntity<?> update(Long id, CreateProductDTO createProductDTO, BindingResult result);
    ResponseEntity<?> updateStock(Long id, UpdateStockDTO updateStockDTO, BindingResult result);
}
