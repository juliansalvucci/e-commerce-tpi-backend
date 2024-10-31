package tpi.backend.e_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpi.backend.e_commerce.services.JwtService.interfaces.IDeleteUserService;
import tpi.backend.e_commerce.services.JwtService.interfaces.IFindUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private IDeleteUserService deleteService;

    @Autowired
    private IFindUserService findService;

    @GetMapping
    public ResponseEntity<?> findAllActive(){
        return findService.findAllActive();
    }

    @GetMapping("/deleted")
    public ResponseEntity<?> findAllDeleted(){
        return findService.findAllDeleted();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return findService.findActiveById(id);
    }

     @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return deleteService.delete(id);
    }

    @PostMapping("/recover/{id}")
    public ResponseEntity<?> recover(@PathVariable Long id){
        return deleteService.recover(id);
    }
}
