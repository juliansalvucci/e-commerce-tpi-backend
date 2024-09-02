package tpi.backend.e_commerce.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpi.backend.e_commerce.dto.ProductDto;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.repositories.ICategoryRepository;
import tpi.backend.e_commerce.services.ICategoryService;
import tpi.backend.e_commerce.services.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;


    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get()); 
            //De existir el producto y estar activo lo devuelve con codigo 200
        }
        return ResponseEntity.notFound().build(); 
        //De no existir el producto o existir y estar eliminado devuelve un codigo 404
    }

    @GetMapping("/deleted")
    public List<Product> findAllDeleted(){
        return productService.findAllDeleted();
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){ //Busca por id entre los productos eliminados
        Optional<Product> optionalProduct = productService.findDeletedById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get()); 
            //De existir el producto y estar eliminado lo devuelve con codigo 200
        }
        return ResponseEntity.notFound().build(); 
        //De no existir el producto o existir y estar activo devuelve un codigo 404
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductDto productDto){ //El producto que obtengo de la peticion no tiene el objeto categoria sino unicamente su id
        Optional<Category> optionalCategory = categoryService.findById(productDto.getCategory()); //Recupero el objeto categoria a traves del id pasado por la peticion
        if (optionalCategory.isEmpty()) { //Si no existe la categoria mandada por la peticion, retorno un 404
            return ResponseEntity.status(404).body("Error: La categoria ingresada no existe");
        }
        Product productToSave = new Product(
            productDto.getName(), productDto.getDescription(), productDto.getPrice(), optionalCategory.get()
        ); //Creo el producto a guardar en la bd

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productToSave));
    }

    @PutMapping("/{id}") //Actualiza un producto
    public ResponseEntity<?> update(@RequestBody ProductDto productDto, @PathVariable Long id){ 
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) { //Primero chequea que exista un producto con ese id
            Optional<Category> optionalCategory = categoryService.findById(productDto.getCategory());
            if (optionalCategory.isEmpty()) {
                return ResponseEntity.status(404).body("Error: La categoria ingresada no existe");
            }

            Product product = new Product(
                id, productDto.getName(), productDto.getDescription(), 
                productDto.getPrice(), optionalCategory.get(), productDto.isDeleted()
            );
            return ResponseEntity.ok(productService.saveProduct(product)); /*
            Como el producto pasado al save tiene un id, no se crea un nuevo producto 
            sino que se actualiza el que tenia ese id */
        }
        return ResponseEntity.notFound().build(); 
        //De no existir un producto con el id mandado lanza un 404
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productService.delete(product);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


}
