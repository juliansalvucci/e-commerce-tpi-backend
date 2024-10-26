package tpi.backend.e_commerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;
import tpi.backend.e_commerce.dto.ProductDTO.ResponseProductDTO;
import tpi.backend.e_commerce.dto.ProductDTO.UpdateStockDTO;
import tpi.backend.e_commerce.services.product.interfaces.IDeleteProductService;
import tpi.backend.e_commerce.services.product.interfaces.IFindProductService;
import tpi.backend.e_commerce.services.product.interfaces.ISaveProductService;


@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:5173") 
public class ProductController {
    
    @Autowired
    private IFindProductService findProductService;
    @Autowired
    private ISaveProductService saveProductService;
    @Autowired
    private IDeleteProductService deleteProductService;

    @GetMapping
    public List<ResponseProductDTO> findAllActive(){

        return findProductService.findAllActive();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        return findProductService.findActiveById(id);
    }

    @GetMapping("/deleted")
    public List<ResponseProductDTO> findAllDeleted(){
        return findProductService.findAllDeleted();
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){ //Busca por id entre los productos eliminados

        return findProductService.findDeletedById(id);
    }  
    
    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name){

        return findProductService.findByName(name);
    } 
    
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CreateProductDTO productDto, BindingResult result){ //El producto que obtengo de la peticion no tiene el objeto categoria sino unicamente su id
        return saveProductService.save(productDto,result);
    }

    @PutMapping("/{id}") //Actualiza un producto
    public ResponseEntity<?> update(@Valid @RequestBody CreateProductDTO productDto , BindingResult result, @PathVariable Long id){ 

        return saveProductService.update(id, productDto,result);
    }

    @PatchMapping("/update-stock/{id}")
    public ResponseEntity<?> updateStock(@Valid @RequestBody UpdateStockDTO productDto, BindingResult result ,@PathVariable Long id){

        return saveProductService.updateStock(id, productDto, result);
    }

    @DeleteMapping("/{id}") //Elimina logicamente un producto por su id 
    public ResponseEntity<?> delete(@PathVariable Long id){

        return deleteProductService.delete(id);
    }

    @PostMapping("/recover/{id}") //Recupera logicamente un producto por su id.
    public ResponseEntity<?> recover(@PathVariable Long id){

        return deleteProductService.recover(id);
    }




}
