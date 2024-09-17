package tpi.backend.e_commerce.services.brand.interfaces;

import org.springframework.http.ResponseEntity;



public interface IDeleteBrandService {
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> recover(Long id);
    
}
