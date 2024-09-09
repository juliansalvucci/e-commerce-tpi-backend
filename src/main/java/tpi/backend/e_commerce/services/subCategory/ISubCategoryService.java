package tpi.backend.e_commerce.services.subCategory;

import java.util.List;
import java.util.Optional;

import tpi.backend.e_commerce.models.SubCategory;

public interface ISubCategoryService {
    
    public SubCategory saveSubCategory(SubCategory SubCategory);

    public List<SubCategory> findAll();
    public List<SubCategory> findAllDeleted();

    public Optional<SubCategory> findById(Long id);
    public Optional<SubCategory> findActiveById(Long id);
    public Optional<SubCategory> findDeletedById(Long id);

    public void delete(SubCategory SubCategory);
}