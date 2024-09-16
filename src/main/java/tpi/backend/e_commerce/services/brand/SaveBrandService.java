package tpi.backend.e_commerce.services.brand;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.BrandDTO;
import tpi.backend.e_commerce.mapper.BrandMapper;
import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.services.brand.interfaces.ISaveBrandService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class SaveBrandService implements ISaveBrandService{
    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private Validation validation;
   @Override
    public ResponseEntity<?> save(Brand brand, BindingResult result) {
        if(brandRepository.existsByName(brand.getName())){
            result.rejectValue("name", "", "Ya existe una marca con ese nombre");
        }
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
       return ResponseEntity.status(201).body(BrandMapper.toDTO(brandRepository.save(brand)));
    }

    @Override
    public ResponseEntity<?> update(Long id, Brand brand, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        if(brandRepository.existsByNameExceptId(brand.getName(), id)){
            return ResponseEntity.badRequest().body("Ya existe una marca con ese nombre");
        }
        Optional<Brand> optionalBrand = brandRepository.findActiveById(id);
        if (optionalBrand.isPresent()) {
            brand.setId(id);
            brand.setCreationDatetime(optionalBrand.get().getCreationDatetime());
            BrandDTO brandDTO = BrandMapper.toDTO(brandRepository.save(brand));
            return ResponseEntity.ok(brandDTO);
        }
        return ResponseEntity.notFound().build();
    }
    

}
