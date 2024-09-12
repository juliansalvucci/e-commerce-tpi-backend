package tpi.backend.e_commerce.services.brand.interfaces;

import org.springframework.http.ResponseEntity;

import tpi.backend.e_commerce.dto.BrandDTO;
import tpi.backend.e_commerce.models.Brand;

public interface ISaveBrandService {
    ResponseEntity<BrandDTO> save(Brand brand);

    ResponseEntity<?> update(Long id, Brand brand);
}
