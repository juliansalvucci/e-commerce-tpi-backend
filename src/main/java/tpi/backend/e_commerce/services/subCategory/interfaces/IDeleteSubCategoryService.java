package tpi.backend.e_commerce.services.subCategory.interfaces;

import org.springframework.http.ResponseEntity;

public interface IDeleteSubCategoryService {
    ResponseEntity<?> delete(Long id);
    ResponseEntity<?> recover(Long id);
}
