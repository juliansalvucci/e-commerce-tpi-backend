package tpi.backend.e_commerce.services.brand;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.dto.BrandDTO;
import tpi.backend.e_commerce.mapper.BrandMapper;
import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.services.brand.interfaces.ISaveBrandService;

@Service
public class SaveBrandService implements ISaveBrandService{
    @Autowired
    private IBrandRepository brandRepository;
    
   @Override
    public ResponseEntity<BrandDTO> save(Brand brand) {
       return ResponseEntity.status(201).body(BrandMapper.toDTO(brandRepository.save(brand)));
    }

    @Override
    public ResponseEntity<?> update(Long id, Brand brand) {

        Optional<Brand> optionalBrand = brandRepository.findActiveById(id);
        if (optionalBrand.isPresent()) {
            brand.setId(id);
            BrandDTO brandDTO = BrandMapper.toDTO(brandRepository.save(brand));
            return ResponseEntity.ok(brandDTO);
        }
        return ResponseEntity.notFound().build();
    }
    

}
