package tpi.backend.e_commerce.mapper;

import tpi.backend.e_commerce.dto.auth.response.JwtAuthenticationResponse;
import tpi.backend.e_commerce.models.User;

public class UserMapper {
    
    public static JwtAuthenticationResponse toDto(User user){
        return JwtAuthenticationResponse.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .build();
    }
}
