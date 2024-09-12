package tpi.backend.e_commerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Double price; 

    private Long stock; 

    private Long stockMin;

    private String imageURL;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private SubCategory SubCategory;

    private boolean deleted; 
    
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
        SubCategory = subCategory;
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
        SubCategory = subCategory;
    }

   
    
    
}

