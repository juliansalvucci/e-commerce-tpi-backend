package tpi.backend.e_commerce.dto.SubCategoryDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateSubCategoryDTO {

    @NotBlank(message = "No puede estar vacio")
    @Size(min = 3, max = 30, message = "Debe tener entre 3 y 30 caracteres")
    private String name; 
    
    @NotNull
    private Long categoryId;

    public CreateSubCategoryDTO( String name, Long categoryId ) {
        this.name = name;
        this.categoryId = categoryId;
    }

    
    
    
}
