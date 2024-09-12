package tpi.backend.e_commerce.services.category.interfaces;

import org.springframework.http.ResponseEntity;

public interface IDeleteCategoryService {
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> recover(Long id);
}
