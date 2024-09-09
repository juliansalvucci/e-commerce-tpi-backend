package tpi.backend.e_commerce.services.subCategory;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.models.SubCategory;
import tpi.backend.e_commerce.repositories.ISubCategoryRepository;

@Service
public class SubCategoryService implements ISubCategoryService{

    @Autowired
    private ISubCategoryRepository subCategoryRepository;

    @Override
    public SubCategory saveSubCategory(SubCategory SubCategory) {
       return subCategoryRepository.save(SubCategory);
    }

    @Override
    public List<SubCategory> findAll() {
        return (List<SubCategory>) subCategoryRepository.findAllActive();
    }

    @Override
    public List<SubCategory> findAllDeleted() {
        return (List<SubCategory>) subCategoryRepository.findAllDeleted();
    }

    @Override
    public Optional<SubCategory> findById(Long id) {
        return subCategoryRepository.findById(id);
        
    }
    @Override
    public Optional<SubCategory> findActiveById(Long id) {
        return subCategoryRepository.findActiveById(id);
        
    }

    @Override
    public Optional<SubCategory> findDeletedById(Long id) {
        
        return subCategoryRepository.findDeletedById(id);
    }

    @Override
    public void delete(SubCategory SubCategory) { //Borrado logico de categoria
        SubCategory.setDeleted(true);
        subCategoryRepository.save(SubCategory);
    }
}
