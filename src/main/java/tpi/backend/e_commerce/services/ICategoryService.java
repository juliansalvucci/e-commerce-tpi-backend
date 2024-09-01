package tpi.backend.e_commerce.services;

import java.util.List;
import java.util.Optional;

import tpi.backend.e_commerce.models.Category;

public interface ICategoryService {
    
    public Category saveCategory(Category category);

    public List<Category> findAll();
    public List<Category> findAllDeleted();

    public Optional<Category> findById(Long id);
    public Optional<Category> findDeletedById(Long id);

    public void delete(Category category);
}