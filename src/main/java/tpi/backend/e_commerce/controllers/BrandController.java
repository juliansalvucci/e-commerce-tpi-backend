package tpi.backend.e_commerce.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpi.backend.e_commerce.dto.BrandDTO;

import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.services.brand.interfaces.IDeleteBrandService;
import tpi.backend.e_commerce.services.brand.interfaces.IFindBrandService;
import tpi.backend.e_commerce.services.brand.interfaces.ISaveBrandService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IFindBrandService findBrandService;
    @Autowired
    private ISaveBrandService saveBrandService;
    @Autowired
    private IDeleteBrandService deleteBrandService;

    @GetMapping
    public List<BrandDTO> findAll(){
        return findBrandService.findAllActive();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return findBrandService.findActiveById(id);
    }

    @GetMapping("/deleted")
    public List<BrandDTO> findAllDeleted(){
        return findBrandService.findAllDeleted();
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){

        return findBrandService.findDeletedById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){
        return findBrandService.findByName(name);
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Brand brand, BindingResult result){
        return saveBrandService.save(brand, result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Brand brand, BindingResult result,@PathVariable Long id){
        return saveBrandService.update(id, brand, result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return deleteBrandService.delete(id);
    }

    @PostMapping("/recover/{id}")
    public ResponseEntity<?> recover(@PathVariable Long id){
       return deleteBrandService.recover(id);
    }
}
