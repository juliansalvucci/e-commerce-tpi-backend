package tpi.backend.e_commerce.services.brand;

import java.time.LocalDateTime;
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
        if (optionalBrand.isEmpty()) {
            
            return ResponseEntity.notFound().build();
        }

        if (brandRepository.hasBrandProducts(id)) {
            //Si la marca tiene productos asociados, retorna el codigo CONFLICT 409
            return ResponseEntity.status(409).body("La marca tiene productos asociados");
        }

        Brand brand = optionalBrand.get();
        brand.setDeleted(true);
        brand.setDeleteDatetime(LocalDateTime.now()); //Guardo la fechaHora en que se elimina al objeto
        brandRepository.save(brand);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> recover(Long id) {
       Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            brand.setDeleted(false);
            brand.setDeleteDatetime(null); //Como el objeto vuelve a estar activo, elimino la fecha de borrado
            return ResponseEntity.ok(BrandMapper.toDTO(brandRepository.save(brand)));
        }    
        return ResponseEntity.notFound().build();
    }
    
    
}
