package tpi.backend.e_commerce.dto;


import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    
    private LocalDateTime creationDatetime;
    private LocalDateTime updateDatetime;
    private LocalDateTime deleteDatetime;

    public CategoryDTO(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CategoryDTO(Long id, String name, String description, LocalDateTime creationDatetime,
            LocalDateTime updateDatetime, LocalDateTime deleteDatetime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creationDatetime = creationDatetime;
        this.updateDatetime = updateDatetime;
        this.deleteDatetime = deleteDatetime;
    }
    
}
