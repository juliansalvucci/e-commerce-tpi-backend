package tpi.backend.e_commerce.dto;

import lombok.Data;

@Data
public class BrandDTO {
    private Long id;

    private String name;

    public BrandDTO(String name) {
        this.name = name;
    }

    public BrandDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    

}
