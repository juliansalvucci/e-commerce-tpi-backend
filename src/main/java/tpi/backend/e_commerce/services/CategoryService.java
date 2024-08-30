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
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        throw new RuntimeException("Product doesn't exists");
    }

    @Override
    public void deleteById(Long id) { //Borrado fisico de categoria
        categoryRepository.deleteById(id);
    }
}
