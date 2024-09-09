package tpi.backend.e_commerce.services.category;

import java.util.List;


import org.springframework.http.ResponseEntity;

import tpi.backend.e_commerce.dto.CategoryDTO;
import tpi.backend.e_commerce.models.Category;


public interface ICategoryService {

    ResponseEntity<CategoryDTO> save(Category category);
    ResponseEntity<?> update(Long id, Category category);
    ResponseEntity<?> recover(Long id);

    List<CategoryDTO> findAllActive();
    List<CategoryDTO> findAllDeleted();

    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> findActiveById(Long id);
    ResponseEntity<?> findDeletedById(Long id);

    ResponseEntity<?> delete(Long id);
}
