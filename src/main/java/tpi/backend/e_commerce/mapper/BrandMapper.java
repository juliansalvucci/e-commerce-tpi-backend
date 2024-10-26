package tpi.backend.e_commerce.mapper;
import java.util.List;
import tpi.backend.e_commerce.dto.BrandDTO;
import tpi.backend.e_commerce.models.Brand;

public class BrandMapper {
    public static BrandDTO toDTO(Brand brand){
        return new BrandDTO(
        brand.getId(), 
        brand.getName(), 
        brand.getCreationDatetime(), 
        brand.getUpdateDatetime(), 
        brand.getDeleteDatetime());
    }

    public static List<BrandDTO> toDTOList(List<Brand> brands){
        return brands.stream()
        .map(b -> BrandMapper.toDTO(b))
        .toList();
    }
}