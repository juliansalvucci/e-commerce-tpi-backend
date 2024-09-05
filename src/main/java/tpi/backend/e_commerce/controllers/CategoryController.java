package tpi.backend.e_commerce.controllers;

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
import java.util.List;
import java.util.Optional;

import tpi.backend.e_commerce.dto.CategoryDTO;
import tpi.backend.e_commerce.mapper.CategoryMapper;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.services.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private ICategoryService categoryService;
    
    @GetMapping
    public List<CategoryDTO> findAll(){
        return CategoryMapper.toDTOList(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            return ResponseEntity.ok(CategoryMapper.toDTO(optionalCategory.get())); 
            //De existir la categoria y estar activa lo devuelve con codigo 200
        }
    
        return ResponseEntity.notFound().build(); 
        //De no existir la categoria o existir y estar eliminada devuelve un codigo 404
    }

    @GetMapping("/deleted")
    public List<CategoryDTO> findAllDeleted(){
        return CategoryMapper.toDTOList(categoryService.findAllDeleted());
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){ //Busca por id entre las categorias eliminadas
        Optional<Category> optionalCategory = categoryService.findDeletedById(id);
        if (optionalCategory.isPresent()) {
            return ResponseEntity.ok(CategoryMapper.toDTO(optionalCategory.get())); 
            //De existir la categoria y estar eliminada lo devuelve con codigo 200
        }
        return ResponseEntity.notFound().build(); 
        //De no existir la categoria o existir y estar activa devuelve un codigo 404
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody Category category){
        return ResponseEntity.ok(CategoryMapper.toDTO(categoryService.saveCategory(category)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category requestCategory){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            Category dbCategory = optionalCategory.get();
            requestCategory.setId(dbCategory.getId());
            return ResponseEntity.ok(CategoryMapper.toDTO(categoryService.saveCategory(requestCategory)));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            categoryService.delete(optionalCategory.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/recover/{id}") //Recupera logicamente una categoria por su id.
    public ResponseEntity<?> recoverCategory(@PathVariable Long id){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            category.setDeleted(false);
            return ResponseEntity.ok(CategoryMapper.toDTO(categoryService.saveCategory(category)));
        }
        return ResponseEntity.notFound().build(); //Si el producto
    }

}
