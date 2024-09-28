package tpi.backend.e_commerce.services.JwtService.interfaces;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.auth.request.SignInRequest;
import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;


public interface IAuthenticationService {
    ResponseEntity<?> signup(SignUpRequest request, BindingResult result);

    ResponseEntity<?> signin(SignInRequest request, BindingResult result);
}
