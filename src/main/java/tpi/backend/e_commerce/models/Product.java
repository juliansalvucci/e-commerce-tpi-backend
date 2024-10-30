package tpi.backend.e_commerce.models;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price; 

    private Long stock; 

    //Este atributo se utilizara para mandar alertas cuando el stock sea menor o igual al stock minimo
    private Long stockMin;

    private String imageURL;

    private String color;

    private String size;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private SubCategory subCategory;

    private boolean deleted; 
    
    private LocalDateTime creationDatetime;
    private LocalDateTime updateDatetime;
    private LocalDateTime deleteDatetime;

    @PrePersist //Este metodo se ejecutara antes de crear persistir al objeto en la BD
    public void preCreate() {
        creationDatetime = LocalDateTime.now();
        trimStrings();
    }

    @PreUpdate //Este metodo se ejecutara antes de actualizarse el objeto en la BD
    public void preUpdate() {
        updateDatetime = LocalDateTime.now();
        trimStrings();
    }

    public Product(String name, String description, Double price, Long stock, Long stockMin, String imageURL,
            String color, String size, Brand brand, SubCategory subCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.stockMin = stockMin;
        this.imageURL = imageURL;
        this.color = color;
        this.size = size;
        this.brand = brand;
        this.subCategory = subCategory;
    }
    
    public Product(Long id, String name, String description, Double price, Long stock, Long stockMin, String imageURL,
            String color, String size, Brand brand, SubCategory subCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.stockMin = stockMin;
        this.imageURL = imageURL;
        this.color = color;
        this.size = size;
        this.brand = brand;
        this.subCategory = subCategory;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
        if (deleted) {
            deleteDatetime = LocalDateTime.now();            
        }else{
            deleteDatetime = null;
        }
    }
   
    private void trimStrings(){
        name = name.trim();
        description = description.trim();
        imageURL = imageURL.trim();
        color = color.trim();
        size = size.trim();
    }
    
}

