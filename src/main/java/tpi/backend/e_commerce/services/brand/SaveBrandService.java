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

        result = brandNameValidations(result, brand.getName());

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

       return ResponseEntity.status(201).body(BrandMapper.toDTO(brandRepository.save(brand)));
    }

    @Override
    public ResponseEntity<?> update(Long id, Brand brand, BindingResult result) {

        if(brandRepository.existsByNameExceptId(brand.getName(), id)){
            result.rejectValue("name", "", "Ya existe una marca con ese nombre");
        }

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        result = brandNameValidations(result, brand.getName());

        if (result.hasFieldErrors()) {
            return validation.validate(result);
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
    
    private BindingResult brandNameValidations(BindingResult result , String name){

        //Chequea que el primer caracter sea un digito o una letra
        char firstChar = name.charAt(0);
        if (!Character.isLetterOrDigit(firstChar)) {
            result.rejectValue(
                "name", 
                "", 
                "El primer caracter debe ser un numero o una letra"
            );     
        }

        //Chequea que al menos un caracter sea una letra
        boolean letra = false;
        for (int i = 0; i < name.length(); i++) {
            if (Character.isLetter(name.charAt(i))) {
                letra = true;
            }
        }
        if (!letra) {
            result.rejectValue(
                "name", 
                "", 
                "El nombre debe contener al menos una letra"
            );
        }

        return result;
     
    } 
}
