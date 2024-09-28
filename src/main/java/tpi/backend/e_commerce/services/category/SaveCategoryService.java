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

        result = categoryNameValidations(result, category.getName());

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        return ResponseEntity.status(201).body(CategoryMapper.toDTO(categoryRepository.save(category)));
    }

    @Override
    public ResponseEntity<?> update(Long id, Category category, BindingResult result) {
        
        if(categoryRepository.existByNameExceptId(category.getName(), id)){
            result.rejectValue("name", "", "Ya existe una categoria con ese nombre");
        }

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        result = categoryNameValidations(result, category.getName());

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        Optional<Category> optionalCategory = categoryRepository.findActiveById(id);
        if (optionalCategory.isPresent()){
            category.setId(id);
            category.setCreationDatetime(optionalCategory.get().getCreationDatetime());
            return ResponseEntity.ok(CategoryMapper.toDTO(categoryRepository.save(category)));
        }
        return ResponseEntity.notFound().build();
    }

    private BindingResult categoryNameValidations(BindingResult result, String name){

        //Chequea que el primer caracter del nombre sea un digito o una letra
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
