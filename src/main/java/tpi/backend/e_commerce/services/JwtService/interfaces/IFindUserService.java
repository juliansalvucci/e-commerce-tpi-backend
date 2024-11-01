package tpi.backend.e_commerce.services.JwtService.interfaces;

import org.springframework.http.ResponseEntity;

public interface IFindUserService {

    ResponseEntity<?> findAllActive();
    ResponseEntity<?> findAllDeleted();
    
    ResponseEntity<?> findActiveById(Long id);
}
