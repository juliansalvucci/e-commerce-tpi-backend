package tpi.backend.e_commerce.dto.ProductDTO;

import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tpi.backend.e_commerce.validation.sizeValidation.SizeValidation;

@Data
public class CreateProductDTO {

    @NotBlank(message = "No puede estar vacio")
    @SizeValidation(min = 2, max = 60)
    private String name;

    @SizeValidation(max = 100)
    @NotBlank(message = "No puede estar vacio")
    private String description;

    @Min(1)
    @NotNull(message = "Debe ingresar un precio")
    private Double price;

    @Min(0)
    @NotNull(message = "Debe ingresar un stock")
    private Long stock = 0L;

    @Min(0)
    @NotNull(message="Debe ingresar un stock minimo")
    private Long stockMin;

    @URL(message = "Debe ser una URL")
    private String imageURL;

    @NotBlank(message = "Debe ingresar un color")
    @SizeValidation(min = 3 , max = 20)
    private String color;
    private String size;
    
    @NotNull(message = "Debe ingresar un id de marca")
    private Long brandId;
    @NotNull(message = "Debe ingresar un id de subcategoria")
    private Long subCategoryId;
    
    public CreateProductDTO(String name, String description, Double price, Long stock, 
        Long stockMin, String imageURL, String color, String size, Long brandId, Long subCategoryId) {
        
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.stockMin = stockMin;
        this.imageURL = imageURL;
        this.brandId = brandId;
        this.subCategoryId = subCategoryId;
        this.color = color;
        this.size = size;
    }
    
}
