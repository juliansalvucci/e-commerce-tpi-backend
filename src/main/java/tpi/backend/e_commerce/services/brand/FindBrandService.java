package tpi.backend.e_commerce.services.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.dto.BrandDTO;
import tpi.backend.e_commerce.mapper.BrandMapper;

import tpi.backend.e_commerce.models.Brand;

import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.services.brand.interfaces.IFindBrandService;

@Service
public class FindBrandService implements IFindBrandService{

    @Autowired
    private IBrandRepository brandRepository;
    
    @Override
    public List<BrandDTO> findAllActive() {
        return BrandMapper.toDTOList((List<Brand>)brandRepository.findAllActive());
    }

    @Override
    public List<BrandDTO> findAllDeleted() {
        return BrandMapper.toDTOList((List<Brand>)brandRepository.findAllDeleted());
    }

    @Override
    public ResponseEntity<?> findActiveById(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findActiveById(id);
        if (optionalBrand.isPresent()) {
            return ResponseEntity.ok(BrandMapper.toDTO(optionalBrand.get())); 

        }
        return ResponseEntity.notFound().build(); 
        
    }

    @Override
    public ResponseEntity<?> findDeletedById(Long id) {
        
        Optional<Brand> optionalBrand = brandRepository.findDeletedById(id);
        if (optionalBrand.isPresent()) {
            return ResponseEntity.ok(BrandMapper.toDTO(optionalBrand.get())); 

        }
        return ResponseEntity.notFound().build(); 
    }

    @Override
    public ResponseEntity<?> findActiveByName(String name) {
        Optional<Brand> optionalBrand = brandRepository.findActiveByName(name);
        if(optionalBrand.isEmpty()){
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(BrandMapper.toDTO(optionalBrand.get()));
    }
    
    
}
