package tpi.backend.e_commerce.services.category;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


import tpi.backend.e_commerce.mapper.CategoryMapper;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.repositories.ICategoryRepository;
import tpi.backend.e_commerce.services.category.interfaces.ISaveCategoryService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class SaveCategoryService implements ISaveCategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private Validation validation;

    @Override
    public ResponseEntity<?> save(Category category, BindingResult result) {
        if(categoryRepository.existByName(category.getName())){
            result.rejectValue("name", "", "Ya existe una categoria con ese nombre");
        }
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
       return ResponseEntity.status(201).body(CategoryMapper.toDTO(categoryRepository.save(category)));
    }

    @Override
    public ResponseEntity<?> update(Long id, Category category, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        if(categoryRepository.existByNameExceptId(category.getName(), id)){
            return ResponseEntity.badRequest().body("Ya existe una categoria con ese nombre");
        }
        Optional<Category> optionalCategory = categoryRepository.findActiveById(id);
        if (optionalCategory.isPresent()){
            category.setId(id);
            category.setCreationDatetime(optionalCategory.get().getCreationDatetime());
            return ResponseEntity.ok(CategoryMapper.toDTO(categoryRepository.save(category)));
        }
        return ResponseEntity.notFound().build();
    }

    

    
}
