package tpi.backend.e_commerce.services.subCategory.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.dto.SubCategoryDTO.CreateSubCategoryDTO;


public interface ISaveSubCategoryService {
    ResponseEntity<?> save(CreateSubCategoryDTO subCategoryDTO);
    ResponseEntity<?> update(Long id, CreateSubCategoryDTO subCategoryDTO);
}
