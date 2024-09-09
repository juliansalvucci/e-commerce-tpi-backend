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

import tpi.backend.e_commerce.dto.SubCategoryDTO;
import tpi.backend.e_commerce.mapper.SubCategoryMapper;
import tpi.backend.e_commerce.models.SubCategory;
import tpi.backend.e_commerce.services.subCategory.ISubCategoryService;



@RestController
@RequestMapping("/subCategory")
public class SubCategoryController {
    
    @Autowired
    private ISubCategoryService subCategoryService;
    
    @GetMapping
    public List<SubCategoryDTO> findAll(){
        return SubCategoryMapper.toDTOList(subCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<SubCategory> optionalSubCategory = subCategoryService.findById(id);
        if (optionalSubCategory.isPresent()) {
            return ResponseEntity.ok(SubCategoryMapper.toDTO(optionalSubCategory.get())); 
            //De existir la categoria y estar activa lo devuelve con codigo 200
        }
    
        return ResponseEntity.notFound().build(); 
        //De no existir la categoria o existir y estar eliminada devuelve un codigo 404
    }

    @GetMapping("/deleted")
    public List<SubCategoryDTO> findAllDeleted(){
        return SubCategoryMapper.toDTOList(subCategoryService.findAllDeleted());
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){ //Busca por id entre las categorias eliminadas
        Optional<SubCategory> optionalSubCategory = subCategoryService.findDeletedById(id);
        if (optionalSubCategory.isPresent()) {
            return ResponseEntity.ok(SubCategoryMapper.toDTO(optionalSubCategory.get())); 
            //De existir la categoria y estar eliminada lo devuelve con codigo 200
        }
        return ResponseEntity.notFound().build(); 
        //De no existir la categoria o existir y estar activa devuelve un codigo 404
    }

    @PostMapping
    public ResponseEntity<SubCategoryDTO> create(@RequestBody SubCategory SubCategory){
        return ResponseEntity.ok(SubCategoryMapper.toDTO(subCategoryService.saveSubCategory(SubCategory)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SubCategory requestSubCategory){
        Optional<SubCategory> optionalSubCategory = subCategoryService.findById(id);
        if (optionalSubCategory.isPresent()) {
            SubCategory dbSubCategory = optionalSubCategory.get();
            requestSubCategory.setId(dbSubCategory.getId());
            return ResponseEntity.ok(SubCategoryMapper.toDTO(subCategoryService.saveSubCategory(requestSubCategory)));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<SubCategory> optionalSubCategory = subCategoryService.findById(id);
        if (optionalSubCategory.isPresent()) {
            subCategoryService.delete(optionalSubCategory.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/recover/{id}") //Recupera logicamente una categoria por su id.
    public ResponseEntity<?> recoverSubCategory(@PathVariable Long id){
        Optional<SubCategory> optionalSubCategory = subCategoryService.findById(id);
        if (optionalSubCategory.isPresent()) {
            SubCategory SubCategory = optionalSubCategory.get();
            SubCategory.setDeleted(false);
            return ResponseEntity.ok(SubCategoryMapper.toDTO(subCategoryService.saveSubCategory(SubCategory)));
        }
        return ResponseEntity.notFound().build(); //Si el producto
    }

}
