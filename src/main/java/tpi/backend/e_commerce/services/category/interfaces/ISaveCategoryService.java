package tpi.backend.e_commerce.services.category.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


import tpi.backend.e_commerce.models.Category;

public interface ISaveCategoryService {
    ResponseEntity<?> save(Category category, BindingResult result);
    ResponseEntity<?> update(Long id, Category category, BindingResult result);
    //void modifyName(Category c);
}
