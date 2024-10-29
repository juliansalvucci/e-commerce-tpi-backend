package tpi.backend.e_commerce.services.subCategory;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.mapper.SubCategoryMapper;
import tpi.backend.e_commerce.models.SubCategory;

import tpi.backend.e_commerce.repositories.ISubCategoryRepository;
import tpi.backend.e_commerce.services.subCategory.interfaces.IDeleteSubCategoryService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class DeleteSubCategoryService implements IDeleteSubCategoryService{
    @Autowired
    private ISubCategoryRepository subCategoryRepository;

    @Autowired
    private Validation validation;
    
    @Override
    public ResponseEntity<?> delete(Long id) { 
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (optionalSubCategory.isEmpty()){    

            return validation.validate(
                "id",
                "No existe una sub categoria con ese id",
                404  
            );
        }
        
        if (subCategoryRepository.hasSubCategoryProducts(id)) {

            return validation.validate(
                "id",
                "La subcategoria tiene productos asociados",
                409
            );
        }

        SubCategory subCategory = optionalSubCategory.get();
        subCategory.setDeleted(true);
        subCategoryRepository.save(subCategory);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> recover(Long id) {

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (optionalSubCategory.isEmpty()){
            return validation.validate(
                "id",
                "No existe una sub categoria con ese id",
                404
            );
        }

        SubCategory subCategory = optionalSubCategory.get();
        subCategory.setDeleted(false);
        return ResponseEntity.ok(SubCategoryMapper.toDTO(subCategoryRepository.save(subCategory)));
    }
}
