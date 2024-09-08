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

    private Double price; //Precio por unidad

    private Long stock; //Unidades en stock

    @ManyToOne
    private SubCategory SubCategory;

    private boolean deleted; //Borrado logico, si el valor es true significa que el producto fue eliminado
    
    public Product() {
    }

    public Product(String name, String description, Double price, SubCategory SubCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.SubCategory = SubCategory;
    }

    public Product(Long id, String name, String description, Double price, SubCategory SubCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.SubCategory = SubCategory;
    }

   
    
    
}

