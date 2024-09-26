package tpi.backend.e_commerce.services.subCategory;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.mapper.SubCategoryMapper;
import tpi.backend.e_commerce.models.SubCategory;

import tpi.backend.e_commerce.repositories.ISubCategoryRepository;
import tpi.backend.e_commerce.services.subCategory.interfaces.IDeleteSubCategoryService;

@Service
public class DeleteSubCategoryService implements IDeleteSubCategoryService{
    @Autowired
    private ISubCategoryRepository subCategoryRepository;


    @Override
    public ResponseEntity<?> delete(Long id) { 
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (optionalSubCategory.isEmpty()){    

            return ResponseEntity.notFound().build();
        }
        
        if (subCategoryRepository.hasSubCategoryProducts(id)) {
            return ResponseEntity.status(409).body("La subcategoria tiene productos asociados");
        }

        SubCategory subCategory = optionalSubCategory.get();
        subCategory.setDeleted(true);
        subCategory.setDeleteDatetime(LocalDateTime.now());
        subCategoryRepository.save(subCategory);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> recover(Long id) {
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (optionalSubCategory.isPresent()){
            SubCategory subCategory = optionalSubCategory.get();
            subCategory.setDeleted(false);
            subCategory.setDeleteDatetime(null);
            return ResponseEntity.ok(SubCategoryMapper.toDTO(subCategoryRepository.save(subCategory)));
        }
        return ResponseEntity.notFound().build();    
    }
}
