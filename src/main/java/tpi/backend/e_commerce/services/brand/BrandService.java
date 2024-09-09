package tpi.backend.e_commerce.services.brand;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.models.Brand;

import tpi.backend.e_commerce.repositories.IBrandRepository;

@Service
public class BrandService implements IBrandService{

    @Autowired
    private IBrandRepository brandRepository;
    
   @Override
    public Brand saveBrand(Brand brand) {
       return brandRepository.save(brand);
    }

    @Override
    public List<Brand> findAll() {
        return (List<Brand>) brandRepository.findAllActive();
    }

    @Override
    public List<Brand> findAllDeleted() {
        return (List<Brand>) brandRepository.findAllDeleted();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Optional<Brand> findActiveById(Long id) {
        return brandRepository.findActiveById(id);
        
    }

    @Override
    public Optional<Brand> findDeletedById(Long id) {
        
        return brandRepository.findDeletedById(id);
    }

    @Override
    public void delete(Brand brand) { //Borrado logico de categoria
        brand.setDeleted(true);
        brandRepository.save(brand);
    }
    
}
