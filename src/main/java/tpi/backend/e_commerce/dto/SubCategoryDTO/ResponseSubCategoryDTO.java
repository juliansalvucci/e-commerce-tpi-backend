package tpi.backend.e_commerce.dto.SubCategoryDTO;

import lombok.Data;

@Data
public class ResponseSubCategoryDTO {
    
    private Long id; 

    private String name; 

    private String description; 
    
    private String category; 

    public ResponseSubCategoryDTO() {
    } 

    public ResponseSubCategoryDTO(Long id, String name, String description, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }


}
