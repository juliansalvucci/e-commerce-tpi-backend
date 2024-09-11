package tpi.backend.e_commerce.dto.ProductDTO;

public class ResponseProductDTO {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private String SubCategory;
    
    public ResponseProductDTO() {
    }

    public ResponseProductDTO(Long id, String name, String description, Double price, String SubCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.SubCategory = SubCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSubCategory() {
        return SubCategory;
    }

    public void setSubCategory(String SubCategory) {
        this.SubCategory = SubCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
