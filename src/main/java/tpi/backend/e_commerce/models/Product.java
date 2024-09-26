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
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    private Double price; 

    private Long stock; 

    private Long stockMin;

    private String imageURL;

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
        subCategory = subCategory;
    }


    public Product(Long id, String name, String description, Double price, Long stock, Long stockMin, String imageURL,
            Brand brand, tpi.backend.e_commerce.models.SubCategory subCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.stockMin = stockMin;
        this.imageURL = imageURL;
        this.brand = brand;
        subCategory = subCategory;
    }

   
    
    
}

