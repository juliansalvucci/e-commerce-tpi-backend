package tpi.backend.e_commerce.services.product;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;
import tpi.backend.e_commerce.dto.ProductDTO.UpdateStockDTO;
import tpi.backend.e_commerce.mapper.ProductMapper;
import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.models.SubCategory;
import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.repositories.ISubCategoryRepository;
import tpi.backend.e_commerce.services.product.interfaces.ISaveProductService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class SaveProductService implements ISaveProductService{

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ISubCategoryRepository subCategoryRepository;
    @Autowired
    private IBrandRepository brandRepository;
    @Autowired
    private Validation validation;
    
    @Override
    public ResponseEntity<?> save(CreateProductDTO createProductDTO , BindingResult result) {

        
        //Este if evita un null pointer exception al hacer el getName() si el nombre es nulo
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        
        if (productRepository.existsByName(createProductDTO.getName())) {
            return validation.validate(
                "name", 
                "Ya existe una producto con ese nombre", 
                409
            );
        }

        result = nameProductValidation(result, createProductDTO.getName()); 
        //Las validaciones de producto estan en un metodo privado aparte
        
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        
        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findActiveById(createProductDTO.getSubCategoryId()); 
        if (optionalSubCategory.isEmpty()) { //Si no existe la categoria mandada por la peticion, retorno un 404

            return validation.validate(
                "subCategoryId", 
                "La subcategoria ingresada no existe", 
                404
            );

        }
        Optional<Brand> optionalBrand = brandRepository.findActiveById(createProductDTO.getBrandId()); 
        if (optionalBrand.isEmpty()) { //Si no existe la marca mandada por la peticion, retorno un 404
           
            return validation.validate(
                "brandId", 
                "La marca ingresada no existe", 
                404
            );

        }
        
        Product productToSave = ProductMapper.toEntity(createProductDTO, optionalSubCategory.get(), optionalBrand.get()); 
        //Convierto el product de la peticion en un product de la bd a traves del mapper

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDTO(productRepository.save(productToSave)));
    }


    @Override
    public ResponseEntity<?> update(Long id, CreateProductDTO createProductDTO, BindingResult result) {

        
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        if (productRepository.existsByNameExceptId(createProductDTO.getName(),id)) {

            return validation.validate(
                "name", 
                "Ya existe un producto con ese nombre", 
                409
            );
        }
       
        result = nameProductValidation(result, createProductDTO.getName()); 
        
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        Optional<Product> optionalProduct = productRepository.findActiveById(id); 
        if (optionalProduct.isEmpty()) { //Si no existe la categoria mandada por la peticion, retorno un 404

            return validation.validate(
                "id", 
                "El id ingresado no corresponde a ningun producto activo", 
                404
            );
           
        }

        Optional<SubCategory> optionalSubCategory = subCategoryRepository.findActiveById(createProductDTO.getSubCategoryId()); 
        if (optionalSubCategory.isEmpty()) { //Si no existe la categoria mandada por la peticion, retorno un 404
   
            return validation.validate(
                "subCategoryId", 
                "La sub categoria ingresada no existe", 
                404
            );

        }

        Optional<Brand> optionalBrand = brandRepository.findActiveById(createProductDTO.getBrandId()); 
        if (optionalBrand.isEmpty()) { //Si no existe la marca mandada por la peticion, retorno un 404

            return validation.validate(
                "brandId", 
                "La marca ingresada no existe", 
                404
            );
           
        }

        Product productToSave = ProductMapper.toUpdate(createProductDTO, id ,optionalSubCategory.get(), optionalBrand.get()); 
        //Convierto el product de la peticion en un product de la bd a traves del mapper

        productToSave.setCreationDatetime(optionalProduct.get().getCreationDatetime());
        //Asigno al producto a guardar en la bd la fecha de creacion que tenia el producto antes de ser actualizado

        return ResponseEntity.ok(ProductMapper.toDTO(productRepository.save(productToSave)));

    }

    @Override
    public ResponseEntity<?> updateStock(Long id, UpdateStockDTO updateStockDTO, BindingResult result) {
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        Optional<Product> optionalProduct = productRepository.findActiveById(id);
        if (optionalProduct.isEmpty()) {
            return validation.validate(
                "id",
                "El id ingresado no corresponde a ningun producto activo",
                404
            );
        }

        Product product = optionalProduct.get();
        product.setStock(updateStockDTO.getStock());
        productRepository.save(product);
        
        return ResponseEntity.ok(ProductMapper.toDTO(product));

    }

    private BindingResult nameProductValidation(BindingResult result, String name) {
        
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
