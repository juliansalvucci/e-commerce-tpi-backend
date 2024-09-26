package tpi.backend.e_commerce.dto.ProductDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseProductDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long stock;
    private Long stockMin;

    private String imageURL;

    private String category;
    private String subCategory;
    private String brand;
    
    private LocalDateTime creationDatetime;
    private LocalDateTime updateDatetime;
    private LocalDateTime deleteDatetime;

    public ResponseProductDTO() {
    }

    public ResponseProductDTO(Long id, String name, String description, Double price, Long stock, 
        Long stockMin, String imageURL, String category, String subCategory, String brand, 
        LocalDateTime creationDatetime, LocalDateTime updateDatetime, 
        LocalDateTime deleteDatetime) {
            
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.stockMin = stockMin;
        this.imageURL = imageURL;
        this.category = category;
        this.subCategory = subCategory;
        this.brand = brand;
        this.creationDatetime = creationDatetime;
        this.updateDatetime = updateDatetime;
        this.deleteDatetime = deleteDatetime;
    }

      
}
