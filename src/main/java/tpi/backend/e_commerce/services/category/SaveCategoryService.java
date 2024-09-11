package tpi.backend.e_commerce.services.category;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.dto.CategoryDTO;
import tpi.backend.e_commerce.mapper.CategoryMapper;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.repositories.ICategoryRepository;
import tpi.backend.e_commerce.services.category.interfaces.ISaveCategoryService;

@Service
public class SaveCategoryService implements ISaveCategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public ResponseEntity<CategoryDTO> save(Category category) {
       return ResponseEntity.status(201).body(CategoryMapper.toDTO(categoryRepository.save(category)));
    }

    @Override
    public ResponseEntity<?> update(Long id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findActiveById(id);
        if (optionalCategory.isPresent()){
            category.setId(id);
            return ResponseEntity.ok(CategoryMapper.toDTO(categoryRepository.save(category)));
        }
        return ResponseEntity.notFound().build();
    }
}
