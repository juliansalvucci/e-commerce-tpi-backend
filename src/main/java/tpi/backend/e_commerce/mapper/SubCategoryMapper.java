package tpi.backend.e_commerce.mapper;

import tpi.backend.e_commerce.dto.SubCategoryDTO;
import tpi.backend.e_commerce.models.SubCategory;
import java.util.List;

public class SubCategoryMapper {
    
    public static SubCategoryDTO toDTO(SubCategory SubCategory){
        return new SubCategoryDTO(SubCategory.getId(), SubCategory.getName(), SubCategory.getDescription());
    } 

   public static SubCategory toEntity(SubCategoryDTO SubCategoryDTO){
        return new SubCategory(SubCategoryDTO.getName(), SubCategoryDTO.getDescription());
    }

    public static List<SubCategoryDTO> toDTOList(List<SubCategory> subCategories){
        return subCategories.stream()
        .map(c -> SubCategoryMapper.toDTO(c))
        .toList();
    }
    
}
