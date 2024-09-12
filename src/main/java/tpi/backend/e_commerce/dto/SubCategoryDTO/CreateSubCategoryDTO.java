package tpi.backend.e_commerce.dto.SubCategoryDTO;

import lombok.Data;

@Data
public class CreateSubCategoryDTO {

    private String name; 

    private String description; 
    
    private Long categoryId;

    public CreateSubCategoryDTO(String name, String description, Long categoryId) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
    }
    
    
}
