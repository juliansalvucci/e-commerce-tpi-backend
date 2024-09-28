package tpi.backend.e_commerce.mapper;

import java.util.List;

import tpi.backend.e_commerce.dto.CategoryDTO;
import tpi.backend.e_commerce.models.Category;

public class CategoryMapper {
    
   public static CategoryDTO toDTO(Category category){
        return new CategoryDTO(
          category.getId(), 
          category.getName(), 
          category.getCreationDatetime(),
          category.getUpdateDatetime(),
          category.getDeleteDatetime());
   }
   public static List<CategoryDTO> toDTOList(List<Category> categories){
        return categories.stream()
        .map(c -> CategoryMapper.toDTO(c))
        .toList();
   }    
}
