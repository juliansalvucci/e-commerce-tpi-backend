package tpi.backend.e_commerce.services.JwtService.interfaces;

import org.springframework.http.ResponseEntity;

public interface IDeleteUserService {
    
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> recover(Long id);
}
