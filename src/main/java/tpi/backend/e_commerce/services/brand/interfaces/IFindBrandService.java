package tpi.backend.e_commerce.services.brand.interfaces;

import java.util.List;


import org.springframework.http.ResponseEntity;

import tpi.backend.e_commerce.dto.BrandDTO;



public interface IFindBrandService {
    
    List<BrandDTO> findAllActive();
    List<BrandDTO> findAllDeleted();

    ResponseEntity<?> findActiveById(Long id);
    ResponseEntity<?> findDeletedById(Long id);

    ResponseEntity<?> findByName(String name);

}
