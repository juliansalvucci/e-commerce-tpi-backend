package tpi.backend.e_commerce.services.subCategory.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.SubCategoryDTO.CreateSubCategoryDTO;


public interface ISaveSubCategoryService {
    ResponseEntity<?> save(CreateSubCategoryDTO subCategoryDTO, BindingResult result);
    ResponseEntity<?> update(Long id, CreateSubCategoryDTO subCategoryDTO, BindingResult result);
}
