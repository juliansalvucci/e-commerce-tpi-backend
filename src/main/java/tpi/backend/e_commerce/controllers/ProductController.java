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

import tpi.backend.e_commerce.dto.CreateProductDTO;
import tpi.backend.e_commerce.dto.ResponseProductDTO;
import tpi.backend.e_commerce.mapper.ProductMapper;
import tpi.backend.e_commerce.models.SubCategory;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.services.product.IProductService;
import tpi.backend.e_commerce.services.subCategory.ISubCategoryService;


@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private IProductService productService;

    @Autowired
    private ISubCategoryService subCategoryService;

    @GetMapping
    public List<ResponseProductDTO> findAll(){

        return ProductMapper.toDTOList(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        Optional<Product> optionalProduct = productService.findActiveById(id);
        if (optionalProduct.isPresent()) {

            ResponseProductDTO productDto = ProductMapper.toDTO(optionalProduct.get()); //Convierto el producto a un DTO
            return ResponseEntity.ok(productDto); 
            //De existir el producto y estar activo lo devuelve con codigo 200
        }
        return ResponseEntity.status(404).body("Error:El id ingresado no corresponde a ningun producto");
        //De no existir el producto o existir y estar eliminado devuelve un codigo 404
    }

    @GetMapping("/deleted")
    public List<ResponseProductDTO> findAllDeleted(){
        return ProductMapper.toDTOList(productService.findAllDeleted());
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){ //Busca por id entre los productos eliminados
        Optional<Product> optionalProduct = productService.findDeletedById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(ProductMapper.toDTO(optionalProduct.get())); 
            //De existir el producto y estar eliminado lo devuelve con codigo 200
        }
        return ResponseEntity.status(404).body("Error:El id ingresado no corresponde a ningun producto eliminado");
        //De no existir el producto o existir y estar activo devuelve un codigo 404
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateProductDTO productDto){ //El producto que obtengo de la peticion no tiene el objeto categoria sino unicamente su id
        Optional<SubCategory> optionalSubCategory = subCategoryService.findById(productDto.getSubCategory()); //Recupero el objeto categoria a traves del id pasado por la peticion
        if (optionalSubCategory.isEmpty()) { //Si no existe la categoria mandada por la peticion, retorno un 404
            return ResponseEntity.status(404).body("Error: La categoria ingresada no existe");
        }
        Product productToSave = ProductMapper.toEntity(productDto, optionalSubCategory.get()); 
        //Convierto el product de la peticion en un product de la bd a traves del mapper

        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.toDTO(productService.saveProduct(productToSave)));
    }

    @PutMapping("/{id}") //Actualiza un producto
    public ResponseEntity<?> update(@RequestBody CreateProductDTO productDto, @PathVariable Long id){ 
        Optional<Product> optionalProduct = productService.findActiveById(id);
        if (optionalProduct.isPresent()) { //Primero chequea que exista un producto con ese id
            Optional<SubCategory> optionalSubCategory = subCategoryService.findById(productDto.getSubCategory());
            if (optionalSubCategory.isEmpty()) {
                return ResponseEntity.status(404).body("Error: La categoria ingresada no existe");
                //Si no existe la categoria mandada por la peticion, retorno un 404
            }
            Product product = ProductMapper.toUpdate(productDto, id, optionalSubCategory.get());

            return ResponseEntity.ok(ProductMapper.toDTO(productService.saveProduct(product))); /*
            Como el producto pasado al save tiene un id, no se crea un nuevo producto 
            sino que se actualiza el que tenia ese id */
        }
        return ResponseEntity.status(404).body("Error:El id ingresado no corresponde a ningun producto");
        //De no existir un producto con el id mandado lanza un 404
    }

    @DeleteMapping("/{id}") //Elimina logicamente un producto por su id 
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Product> optionalProduct = productService.findActiveById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productService.delete(product);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(404).body("Error:El id ingresado no corresponde a ningun producto");
    }

    @GetMapping("/recover/{id}") //Recupera logicamente un producto por su id.
    public ResponseEntity<?> recoverProduct(@PathVariable Long id){
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setDeleted(false);
            return ResponseEntity.ok(ProductMapper.toDTO(productService.saveProduct(product)));
        }
        return ResponseEntity.status(404).body("Error:El id ingresado no corresponde a ningun producto");
        /*
        Disclaimer: Si se ingresa el id de un producto que existe y esta activo,
        se tratara de la misma manera que si estuviera eliminado. Se setea el atributo
        deleted en false (Ya estaba en false porque esta activo)
        */
    }




}
