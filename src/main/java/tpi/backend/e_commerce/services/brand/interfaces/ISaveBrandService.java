package tpi.backend.e_commerce.services.brand.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;


import tpi.backend.e_commerce.models.Brand;

public interface ISaveBrandService {
    ResponseEntity<?> save(Brand brand, BindingResult result);

    ResponseEntity<?> update(Long id, Brand brand, BindingResult result);
}
