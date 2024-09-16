package tpi.backend.e_commerce.services.product.interfaces;

import org.springframework.http.ResponseEntity;

public interface IDeleteProductService {

    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> recover(Long id);

}
