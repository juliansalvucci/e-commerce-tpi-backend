package tpi.backend.e_commerce.services.subCategory.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;


import tpi.backend.e_commerce.dto.SubCategoryDTO.ResponseSubCategoryDTO;


public interface IFindSubCategoryService {
    
    
    
    List<ResponseSubCategoryDTO> findAllActive();
    List<ResponseSubCategoryDTO> findAllDeleted();
    
    ResponseEntity<?> findById(Long id);
    ResponseEntity<?> findActiveById(Long id);
    ResponseEntity<?> findDeletedById(Long id);

}