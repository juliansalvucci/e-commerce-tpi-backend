package tpi.backend.e_commerce.services.subCategory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import tpi.backend.e_commerce.dto.SubCategoryDTO.CreateSubCategoryDTO;
import tpi.backend.e_commerce.mapper.SubCategoryMapper;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.models.SubCategory;
import tpi.backend.e_commerce.repositories.ICategoryRepository;
import tpi.backend.e_commerce.repositories.ISubCategoryRepository;
import tpi.backend.e_commerce.services.subCategory.interfaces.ISaveSubCategoryService;
import tpi.backend.e_commerce.validation.Validation;

import org.springframework.validation.BindingResult;


@Service
public class SaveSubCategoryService implements ISaveSubCategoryService{

    @Autowired
    private ISubCategoryRepository subCategoryRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private Validation validation;

    @Override
    public ResponseEntity<?> save(CreateSubCategoryDTO subCategoryDTO, BindingResult result) {
        
        if(subCategoryRepository.existByName(subCategoryDTO.getName())){
           return validation.validate(
            "name", 
            "Ya existe una sub categoria con ese nombre", 
            409
            );
        }

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        result = subCategoryNameValidations(result, subCategoryDTO.getName());

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        
        Optional<Category> optionalCategory = categoryRepository.findActiveById(subCategoryDTO.getCategoryId());
        if (optionalCategory.isEmpty()) {
            return validation.validate(
                "categoryId", 
                "La categoria ingresada no existe", 
                404
            );
        }
        
            SubCategory subCategory = SubCategoryMapper.toEntity(subCategoryDTO, optionalCategory.get());
            subCategoryRepository.save(subCategory);
            return ResponseEntity.status(201).body(SubCategoryMapper.toDTO(subCategory));
    }

    @Override
    public ResponseEntity<?> update(Long id, CreateSubCategoryDTO subCategoryDTO, 
        BindingResult result) {

        //Chequea que no exista otra subcategoria con el mismo nombre
        if(subCategoryRepository.existByNameExceptId(subCategoryDTO.getName(),id)){
            return validation.validate(
                "name", 
                "Ya existe una sub categoria con ese nombre", 
                409
            );
        }    

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        
        result = subCategoryNameValidations(result, subCategoryDTO.getName());
        
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findActiveById(id);
        if (optionalSubCategory.isEmpty()){
           
            return validation.validate(
                "id", 
                "El id no corresponde a ninguna sub categoria", 
                404
            );
        }

        Optional<Category> optionalCategory = categoryRepository.findActiveById(subCategoryDTO.getCategoryId());
        if (optionalCategory.isEmpty()) {
           
            return validation.validate(
                "categoryId",
              "La categoria ingresada no existe",
               404
            );
        }
                     
        SubCategory subCategory = SubCategoryMapper.toUpdate(id, subCategoryDTO, optionalCategory.get());
        subCategory.setCreationDatetime(optionalSubCategory.get().getCreationDatetime());   
        subCategoryRepository.save(subCategory);
        return ResponseEntity.ok(SubCategoryMapper.toDTO(subCategory));
    }

    private BindingResult subCategoryNameValidations(BindingResult result, String name){

        //Chequea que el primer caracter del nombre sea una una letra
        char firstChar = name.charAt(0);
        if (!Character.isLetter(firstChar)) {
            result.rejectValue(
                "name", 
                "", 
                "El primer caracter debe ser una letra"
            );     
        }

        //Chequea que el nombre contenga al menos una letra
        boolean letra = false;
        for (int i = 0; i < name.length(); i++) {
            if (Character.isLetter(name.charAt(i))) {
                letra = true;
            }
        }
        
        if (!letra) {
            result.rejectValue(
                "name", 
                "", 
                "El nombre debe contener al menos una letra"
            );
        }

        return result;
    }

}
