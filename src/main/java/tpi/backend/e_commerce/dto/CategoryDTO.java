package tpi.backend.e_commerce.dto;


import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    
    public CategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    
}
