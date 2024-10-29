package tpi.backend.e_commerce.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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
    }
    @PreUpdate //Este metodo se ejecutara antes de actualizarse el objeto en la BD
    public void preUpdate() {
        updateDatetime = LocalDateTime.now();
    }

    public Product() {
    }


    public Product(String name, String description, Double price, Long stock, Long stockMin, String imageURL,
            Brand brand, SubCategory subCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.stockMin = stockMin;
        this.imageURL = imageURL;
        this.brand = brand;
        this.subCategory = subCategory;
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
            String color, String size, Brand brand, SubCategory subCategory, boolean deleted,
            LocalDateTime creationDatetime, LocalDateTime updateDatetime, LocalDateTime deleteDatetime) {
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
        this.deleted = deleted;
        this.creationDatetime = creationDatetime;
        this.updateDatetime = updateDatetime;
        this.deleteDatetime = deleteDatetime;
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
   
    
    
}

