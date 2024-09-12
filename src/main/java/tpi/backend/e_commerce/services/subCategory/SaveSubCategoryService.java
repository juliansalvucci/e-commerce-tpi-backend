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

@Service
public class SaveSubCategoryService implements ISaveSubCategoryService{

    @Autowired
    private ISubCategoryRepository subCategoryRepository;

    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public ResponseEntity<?> save(CreateSubCategoryDTO subCategoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findActiveById(subCategoryDTO.getCategoryId());
        if (optionalCategory.isPresent()) {
            SubCategory subCategory = SubCategoryMapper.toEntity(subCategoryDTO, optionalCategory.get());
            subCategoryRepository.save(subCategory);
            return ResponseEntity.status(201).body(SubCategoryMapper.toDTO(subCategory));
        }
        return ResponseEntity.status(404).body("La categoria ingresada no existe");
    }
    @Override
    public ResponseEntity<?> update(Long id, CreateSubCategoryDTO subCategoryDTO) {
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findActiveById(id);
        if (optionalSubCategory.isPresent()){
            Optional<Category> optionalCategory = categoryRepository.findActiveById(subCategoryDTO.getCategoryId());
            if (optionalCategory.isPresent()) {
                SubCategory subCategory = SubCategoryMapper.toUpdate(id, subCategoryDTO, optionalCategory.get());
                subCategoryRepository.save(subCategory);
                return ResponseEntity.ok(SubCategoryMapper.toDTO(subCategory));
            }
            return ResponseEntity.status(404).body("La categoria no existe");

        }
        return ResponseEntity.status(404).body("La sub categoria no existe");
    }
}
