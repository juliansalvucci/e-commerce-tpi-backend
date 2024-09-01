package tpi.backend.e_commerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.repositories.ICategoryRepository;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category) {
       return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAllActive();
    }

    @Override
    public List<Category> findAllDeleted() {
        return (List<Category>) categoryRepository.findAllDeleted();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findActiveById(id);
        
    }

    @Override
    public Optional<Category> findDeletedById(Long id) {
        
        return categoryRepository.findDeletedById(id);
    }

    @Override
    public void delete(Category category) { //Borrado logico de categoria
        category.setDeleted(true);
        categoryRepository.save(category);
    }
}
