package tpi.backend.e_commerce.services.brand;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import tpi.backend.e_commerce.mapper.BrandMapper;
import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.services.brand.interfaces.IDeleteBrandService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class DeleteBrandService implements IDeleteBrandService{

    @Autowired
    private IBrandRepository brandRepository;

    @Autowired
    private Validation validation;
    
    @Override
    public ResponseEntity<?> delete(Long id) { 
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isEmpty()) {
            
            return validation.validate(
                "id",
                "No existe una marca con ese id",
                404
            );
        }

        if (brandRepository.hasBrandProducts(id)) {
            //Si la marca tiene productos asociados, retorna el codigo CONFLICT 409
            return validation.validate(
                "id",
                "La marca tiene productos asociados",
                409
            );
        }

        Brand brand = optionalBrand.get();
        brand.setDeleted(true);
        brandRepository.save(brand);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<?> recover(Long id) {

       Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isEmpty()) {
            return validation.validate(
                "id", 
              "No existe una marca con ese id", 
               404
            );
        }    

        Brand brand = optionalBrand.get();
        brand.setDeleted(false);
        return ResponseEntity.ok(BrandMapper.toDTO(brandRepository.save(brand)));
    }
    
}
