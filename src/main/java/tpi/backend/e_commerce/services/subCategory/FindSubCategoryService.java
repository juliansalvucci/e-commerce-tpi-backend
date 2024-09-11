package tpi.backend.e_commerce.services.subCategory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import tpi.backend.e_commerce.dto.SubCategoryDTO.ResponseSubCategoryDTO;
import tpi.backend.e_commerce.mapper.SubCategoryMapper;

import tpi.backend.e_commerce.models.SubCategory;

import tpi.backend.e_commerce.repositories.ISubCategoryRepository;
import tpi.backend.e_commerce.services.subCategory.interfaces.IFindSubCategoryService;

@Service
public class FindSubCategoryService implements IFindSubCategoryService{

    @Autowired
    private ISubCategoryRepository subCategoryRepository;

    

    @Override
    public List<ResponseSubCategoryDTO> findAllActive() {
        return SubCategoryMapper.toDTOList((List<SubCategory>) subCategoryRepository.findAllActive());
    }

    @Override
    public List<ResponseSubCategoryDTO> findAllDeleted() {
        return SubCategoryMapper.toDTOList((List<SubCategory>) subCategoryRepository.findAllDeleted());
    }

    @Override
    public ResponseEntity<?> findById(Long id) {

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findById(id);
        if (optionalSubCategory.isPresent()){    
            return ResponseEntity.ok(SubCategoryMapper.toDTO(optionalSubCategory.get())); 
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> findActiveById(Long id) {

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findActiveById(id);
        if (optionalSubCategory.isPresent()){    
            return ResponseEntity.ok(SubCategoryMapper.toDTO(optionalSubCategory.get())); 
        }
        return ResponseEntity.notFound().build();

    }

    @Override
    public ResponseEntity<?> findDeletedById(Long id) {

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findDeletedById(id);
        if (optionalSubCategory.isPresent()){    
            return ResponseEntity.ok(SubCategoryMapper.toDTO(optionalSubCategory.get())); 
        }
        return ResponseEntity.notFound().build();
    }



}
