package tpi.backend.e_commerce.services.product.interfaces;

import java.util.List;


import org.springframework.http.ResponseEntity;

import tpi.backend.e_commerce.dto.ProductDTO.ResponseProductDTO;


public interface IFindProductService {
    
    List<ResponseProductDTO> findAllActive();
    List<ResponseProductDTO> findAllDeleted();

    ResponseEntity<?> findActiveById(Long id);
    ResponseEntity<?> findDeletedById(Long id);   
    
}   