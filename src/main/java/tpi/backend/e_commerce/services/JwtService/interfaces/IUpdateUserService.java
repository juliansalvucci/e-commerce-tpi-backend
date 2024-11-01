package tpi.backend.e_commerce.services.JwtService.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.auth.request.UpdateUserDto;


public interface IUpdateUserService {
    ResponseEntity<?> update(Long id, UpdateUserDto userDto, BindingResult result);
}
