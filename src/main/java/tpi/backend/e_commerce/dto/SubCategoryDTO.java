package tpi.backend.e_commerce.dto;

public class SubCategoryDTO {
    
    private Long id; //Id de la categoria

    private String name; //Nombre de la categoria

    private String description; //Descripción de la categoria
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public SubCategoryDTO() {
    } 

    public SubCategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
