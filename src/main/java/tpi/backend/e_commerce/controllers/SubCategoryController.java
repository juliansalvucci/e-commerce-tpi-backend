package tpi.backend.e_commerce.controllers;

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

import java.util.List;

import tpi.backend.e_commerce.dto.SubCategoryDTO.CreateSubCategoryDTO;
import tpi.backend.e_commerce.dto.SubCategoryDTO.ResponseSubCategoryDTO;

import tpi.backend.e_commerce.services.subCategory.interfaces.IDeleteSubCategoryService;
import tpi.backend.e_commerce.services.subCategory.interfaces.IFindSubCategoryService;
import tpi.backend.e_commerce.services.subCategory.interfaces.ISaveSubCategoryService;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {
    
    @Autowired
    private IFindSubCategoryService findSubCategoryService;
    @Autowired
    private ISaveSubCategoryService saveSubCategoryService;
    @Autowired
    private IDeleteSubCategoryService deleteSubCategoryService;
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        
        return findSubCategoryService.findActiveById(id);
    }

    @GetMapping
    public List<ResponseSubCategoryDTO> findAll(){
        return findSubCategoryService.findAllActive();
    }

    @GetMapping("/deleted")
    public List<ResponseSubCategoryDTO> findAllDeleted(){
        return findSubCategoryService.findAllDeleted();
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){ //Busca por id entre las categorias eliminadas
        return findSubCategoryService.findDeletedById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){

        return findSubCategoryService.findByName(name);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateSubCategoryDTO subCategoryDTO, BindingResult result){
        return saveSubCategoryService.save(subCategoryDTO, result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CreateSubCategoryDTO subCategoryDTO,BindingResult result ,@PathVariable Long id){
        return saveSubCategoryService.update(id, subCategoryDTO, result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return deleteSubCategoryService.delete(id);
    }

    @PostMapping("/recover/{id}") //Recupera logicamente una categoria por su id.
    public ResponseEntity<?> recover(@PathVariable Long id){
       return deleteSubCategoryService.recover(id);
    }

}
