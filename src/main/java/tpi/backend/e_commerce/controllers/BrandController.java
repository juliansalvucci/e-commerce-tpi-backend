package tpi.backend.e_commerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpi.backend.e_commerce.dto.BrandDTO;
import tpi.backend.e_commerce.mapper.BrandMapper;

import tpi.backend.e_commerce.models.Brand;

import tpi.backend.e_commerce.services.IBrandService;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    @GetMapping
    public List<BrandDTO> findAll(){
        return BrandMapper.toDTOList(brandService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Brand> optionalBrand = brandService.findActiveById(id);
        if (optionalBrand.isPresent()) {
            return ResponseEntity.ok(BrandMapper.toDTO(optionalBrand.get())); 

        }
        return ResponseEntity.notFound().build(); 
    }

    @GetMapping("/deleted")
    public List<BrandDTO> findAllDeleted(){
        return BrandMapper.toDTOList(brandService.findAllDeleted());
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){
        Optional<Brand> optionalBrand = brandService.findDeletedById(id);
        if (optionalBrand.isPresent()) {
            return ResponseEntity.ok(BrandMapper.toDTO(optionalBrand.get())); 
        }
        return ResponseEntity.notFound().build(); 
    }

    @PostMapping
    public ResponseEntity<BrandDTO> create(@RequestBody Brand brand){

        BrandDTO brandDTO = BrandMapper.toDTO(brandService.saveBrand(brand));
        return ResponseEntity.status(HttpStatus.CREATED).body(brandDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Brand brand, @PathVariable Long id){
        Optional<Brand> optionalBrand = brandService.findActiveById(id);
        if (optionalBrand.isPresent()) {
            brand.setId(id);
            BrandDTO brandDTO = BrandMapper.toDTO(brandService.saveBrand(brand));
            return ResponseEntity.ok(brandDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Brand> optionalBrand = brandService.findById(id);
        if (optionalBrand.isPresent()) {
            brandService.delete(optionalBrand.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/recover/{id}")
    public ResponseEntity<?> recover(@PathVariable Long id){
        Optional<Brand> optionalBrand = brandService.findById(id);
        if (optionalBrand.isPresent()) {
            Brand brand = optionalBrand.get();
            brand.setDeleted(false);
            return ResponseEntity.ok(BrandMapper.toDTO(brandService.saveBrand(brand)));
        }    
        return ResponseEntity.notFound().build();
    }
}
