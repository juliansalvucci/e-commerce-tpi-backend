package tpi.backend.e_commerce.dto.SubCategoryDTO;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseSubCategoryDTO {
    
    private Long id; 

    private String name; 

    
    private String category; 

    private LocalDateTime creationDatetime;
    private LocalDateTime updateDatetime;
    private LocalDateTime deleteDatetime;

    public ResponseSubCategoryDTO() {
    }

    public ResponseSubCategoryDTO(Long id, String name, String category,
            LocalDateTime creationDatetime, LocalDateTime updateDatetime, LocalDateTime deleteDatetime) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.creationDatetime = creationDatetime;
        this.updateDatetime = updateDatetime;
        this.deleteDatetime = deleteDatetime;
    } 

}
