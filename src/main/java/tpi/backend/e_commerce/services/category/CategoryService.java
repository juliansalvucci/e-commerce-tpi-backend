package tpi.backend.e_commerce.services.category;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.dto.CategoryDTO;
import tpi.backend.e_commerce.mapper.CategoryMapper;
import tpi.backend.e_commerce.models.Category;

import tpi.backend.e_commerce.repositories.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public ResponseEntity<CategoryDTO> save(Category category) {
       return ResponseEntity.ok(CategoryMapper.toDTO(categoryRepository.save(category)));
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
    @Override
    public ResponseEntity<?> recover(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setDeleted(false);
            return ResponseEntity.ok(CategoryMapper.toDTO(categoryRepository.save(category)));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public List<CategoryDTO> findAllActive() {
        return CategoryMapper.toDTOList(
            (List<Category>) categoryRepository.findAllActive() 
        );
    }

    @Override
    public List<CategoryDTO> findAllDeleted() {
        return CategoryMapper.toDTOList(
            (List<Category>) categoryRepository.findAllDeleted() 
        );    
    }


    @Override
    public ResponseEntity<?> findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){    
            return ResponseEntity.ok(CategoryMapper.toDTO(optionalCategory.get())); 
        }
        return ResponseEntity.notFound().build();
        
    }
    @Override
    public ResponseEntity<?> findActiveById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findActiveById(id);
        if (optionalCategory.isPresent()){    
            return ResponseEntity.ok(CategoryMapper.toDTO(optionalCategory.get())); 
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> findDeletedById(Long id) {

        Optional<Category> optionalCategory = categoryRepository.findDeletedById(id);
        if (optionalCategory.isPresent()){    
            return ResponseEntity.ok(CategoryMapper.toDTO(optionalCategory.get())); 
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> delete(Long id) { //Borrado logico de categoria
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()){
            Category category = optionalCategory.get();
            category.setDeleted(true);
            return this.save(category);
        }
        return ResponseEntity.notFound().build();
    }
}
