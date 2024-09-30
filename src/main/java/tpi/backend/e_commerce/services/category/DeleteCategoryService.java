package tpi.backend.e_commerce.services.category;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import tpi.backend.e_commerce.mapper.CategoryMapper;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.repositories.ICategoryRepository;
import tpi.backend.e_commerce.services.category.interfaces.IDeleteCategoryService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class DeleteCategoryService implements IDeleteCategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private Validation validation;
    
    @Override
    public ResponseEntity<?> delete(Long id) { //Borrado logico de categoria

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            return validation.validate(
                "id",
                "No existe una categoria con ese id",
                404
            );
        }
        
        //Chequea que la categoria no tenga subcategorias asignadas
        if (categoryRepository.hasCategorySubCategories(id)) {

            return validation.validate(
                "id",
                "La categoria tiene subcategorias asociadas",
                409
            );

        }

        Category category = optionalCategory.get();
        category.setDeleted(true);
        return ResponseEntity.ok(CategoryMapper.toDTO(categoryRepository.save(category)));
    }

    @Override
    public ResponseEntity<?> recover(Long id) {

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()){
            return validation.validate(
                "id",
                "No existe una categoria con ese id",
                404
            );
        }

        Category category = optionalCategory.get();
        category.setDeleted(false);
        return ResponseEntity.ok(CategoryMapper.toDTO(categoryRepository.save(category)));
    }
}
