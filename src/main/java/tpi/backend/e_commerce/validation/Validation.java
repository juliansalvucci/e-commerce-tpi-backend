package tpi.backend.e_commerce.validation;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.Map;
import java.util.HashMap;

@Component
public class Validation {
    
    public ResponseEntity<Map<String,String>> validate(BindingResult result){
        Map<String,String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);    
    }
    
}
