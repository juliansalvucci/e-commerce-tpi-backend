package tpi.backend.e_commerce.services.category.interfaces;

import org.springframework.http.ResponseEntity;

import tpi.backend.e_commerce.dto.CategoryDTO;
import tpi.backend.e_commerce.models.Category;

public interface ISaveCategoryService {
    ResponseEntity<CategoryDTO> save(Category category);
    ResponseEntity<?> update(Long id, Category category);
}
