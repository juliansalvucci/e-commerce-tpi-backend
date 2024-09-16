package tpi.backend.e_commerce.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BrandDTO {
    private Long id;

    private String name;

    private LocalDateTime creationDatetime;
    private LocalDateTime updateDatetime;
    private LocalDateTime deleteDatetime;

    public BrandDTO(Long id, String name, LocalDateTime creationDatetime, LocalDateTime updateDatetime,
            LocalDateTime deleteDatetime) {
        this.id = id;
        this.name = name;
        this.creationDatetime = creationDatetime;
        this.updateDatetime = updateDatetime;
        this.deleteDatetime = deleteDatetime;
    }

}
