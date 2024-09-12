package tpi.backend.e_commerce.services.brand;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.mapper.BrandMapper;
import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.services.brand.interfaces.IDeleteBrandService;

@Service
public class DeleteBrandService implements IDeleteBrandService{

    @Autowired
    private IBrandRepository brandRepository;

    @Override
    public ResponseEntity<?> delete(Long id) { 
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            brand.setDeleted(true);
            brandRepository.save(brand);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> recover(Long id) {
       Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            brand.setDeleted(false);
            return ResponseEntity.ok(BrandMapper.toDTO(brandRepository.save(brand)));
        }    
        return ResponseEntity.notFound().build();
    }

    
}
