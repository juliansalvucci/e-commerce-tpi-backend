package tpi.backend.e_commerce.services.category.interfaces;

import java.util.List;


import org.springframework.http.ResponseEntity;

import tpi.backend.e_commerce.dto.CategoryDTO;



public interface IFindCategoryService {

   
    
    List<CategoryDTO> findAllActive();
    List<CategoryDTO> findAllDeleted();
    
    ResponseEntity<?> findActiveById(Long id);
    ResponseEntity<?> findDeletedById(Long id);
    
    ResponseEntity<?> findByName(String name);

}
