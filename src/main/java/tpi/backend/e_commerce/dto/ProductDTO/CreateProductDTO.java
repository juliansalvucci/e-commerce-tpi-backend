package tpi.backend.e_commerce.dto.ProductDTO;

import lombok.Data;

@Data
public class CreateProductDTO {

    private String name;

    private String description;

    private Double price;

    private Long stock;
    private Long stockMin;

    private String imageURL;

    private Long brandId;
    private Long subCategoryId;
    
    public CreateProductDTO(String name, String description, Double price, Long stock, Long stockMin, String imageURL,
        Long brandId, Long subCategoryId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.stockMin = stockMin;
        this.imageURL = imageURL;
        this.brandId = brandId;
        this.subCategoryId = subCategoryId;
    }
    
}
