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
import java.util.Collections;

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

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        
        Optional<Category> optionalCategory = categoryRepository.findActiveById(subCategoryDTO.getCategoryId());
        if (optionalCategory.isPresent()) {
            SubCategory subCategory = SubCategoryMapper.toEntity(subCategoryDTO, optionalCategory.get());
            subCategoryRepository.save(subCategory);
            return ResponseEntity.status(201).body(SubCategoryMapper.toDTO(subCategory));
        }
        return ResponseEntity.status(404).body(
            Collections.singletonMap("categoryId","La categoria ingresada no existe")
            );
    }

    @Override
    public ResponseEntity<?> update(Long id, CreateSubCategoryDTO subCategoryDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findActiveById(id);
        if (optionalSubCategory.isPresent()){
            Optional<Category> optionalCategory = categoryRepository.findActiveById(subCategoryDTO.getCategoryId());
            if (optionalCategory.isPresent()) {
                SubCategory subCategory = SubCategoryMapper.toUpdate(id, subCategoryDTO, optionalCategory.get());

                subCategory.setCreationDatetime(optionalSubCategory.get().getCreationDatetime());   
                subCategoryRepository.save(subCategory);
                return ResponseEntity.ok(SubCategoryMapper.toDTO(subCategory));
            }
            return ResponseEntity.status(404).body(
                Collections.singletonMap("categoryId","La categoria ingresada no existe")
            );

        }
        return ResponseEntity.status(404).body(
            Collections.singletonMap("id","El id no corresponde a ninguna sub categoria")
        );
    }
}
