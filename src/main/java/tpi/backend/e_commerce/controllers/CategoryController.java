package tpi.backend.e_commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpi.backend.e_commerce.dto.CategoryDTO;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.services.category.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private ICategoryService categoryService;
    
    @GetMapping
    public List<CategoryDTO> findAllActive(){
        return categoryService.findAllActive();
    }
    @GetMapping("/deleted")
    public List<CategoryDTO> findAllDeleted(){
        return categoryService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findActiveById(@PathVariable Long id){
        return categoryService.findActiveById(id);
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){
        return categoryService.findDeletedById(id);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@RequestBody Category category){
        return categoryService.save(category);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category category){
        return categoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return categoryService.delete(id);
    }

    @GetMapping("/recover/{id}")
    public ResponseEntity<?> recover(@PathVariable Long id){
        return categoryService.recover(id);
    }
}
