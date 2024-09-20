package tpi.backend.e_commerce.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpi.backend.e_commerce.dto.CategoryDTO;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.services.category.interfaces.IDeleteCategoryService;
import tpi.backend.e_commerce.services.category.interfaces.IFindCategoryService;
import tpi.backend.e_commerce.services.category.interfaces.ISaveCategoryService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private IFindCategoryService findCategoryService;
    @Autowired
    private ISaveCategoryService saveCategoryService;
    @Autowired
    private IDeleteCategoryService deleteCategoryService;
    
    @GetMapping
    public List<CategoryDTO> findAllActive(){
        return findCategoryService.findAllActive();
    }
    @GetMapping("/deleted")
    public List<CategoryDTO> findAllDeleted(){
        return findCategoryService.findAllDeleted();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findActiveById(@PathVariable Long id){
        return findCategoryService.findActiveById(id);
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){
        return findCategoryService.findDeletedById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        return findCategoryService.findByName(name);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Category category, BindingResult result){
        return saveCategoryService.save(category, result);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Category category,BindingResult result ,@PathVariable Long id){
        return saveCategoryService.update(id, category, result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return deleteCategoryService.delete(id);
    }

    @GetMapping("/recover/{id}")
    public ResponseEntity<?> recover(@PathVariable Long id){
        return deleteCategoryService.recover(id);
    }
}
