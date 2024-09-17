package tpi.backend.e_commerce.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpi.backend.e_commerce.models.Product;

import tpi.backend.e_commerce.services.IProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:5173") 
public class ProductController {
    
    @Autowired
    private IProductService productService;

    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(optionalProduct.get()); //De existir el producto y estar activo lo devuelve con codigo 200
        }
        return ResponseEntity.notFound().build(); //De no existir el producto o existir y estar eliminado devuelve un codigo 404
    }

    @GetMapping("/deleted")
    public List<Product> findAllDeleted(){
        return productService.findAllDeleted();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product){
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PutMapping("/{id}") //Actualiza un producto
    public ResponseEntity<?> update(@RequestBody Product requestProduct, @PathVariable Long id){ 
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) { //Primero chequea que exista un producto con ese id
            Product dbProduct = optionalProduct.get();
            requestProduct.setId(dbProduct.getId()); //Setea el id del producto buscado en la BD al producto recibido en la request
            return ResponseEntity.ok(productService.saveProduct(requestProduct)); /*
            Como el producto pasado al save tiene un id, no se crea un nuevo producto 
            sino que se actualiza el que tenia ese id */
        }
        return ResponseEntity.notFound().build(); //De no existir un producto con el id mandado lanza un 404
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
