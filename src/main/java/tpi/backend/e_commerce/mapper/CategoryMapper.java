package tpi.backend.e_commerce.mapper;

import tpi.backend.e_commerce.dto.CategoryDTO;
import tpi.backend.e_commerce.models.Category;
import java.util.List;

public class CategoryMapper {
    
    public static CategoryDTO toDTO(Category category){
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    } 

   public static Category toEntity(CategoryDTO categoryDTO){
        return new Category(categoryDTO.getName(), categoryDTO.getDescription());
    }

    public static List<CategoryDTO> toDTOList(List<Category> categories){
        return categories.stream()
        .map(c -> CategoryMapper.toDTO(c))
        .toList();
    }
    
}
