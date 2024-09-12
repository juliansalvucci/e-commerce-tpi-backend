package tpi.backend.e_commerce.models;

import java.time.LocalDateTime;

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
public class SubCategory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private Category category;

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
    public SubCategory() {
    }

    public SubCategory(String name) {
        this.name = name;
    }

    public SubCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public SubCategory(String name, String description, Category category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    public SubCategory(Long id, String name, String description, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }
    
    
}