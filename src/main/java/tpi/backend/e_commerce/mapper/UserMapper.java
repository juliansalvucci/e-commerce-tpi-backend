package tpi.backend.e_commerce.mapper;


import tpi.backend.e_commerce.dto.auth.response.ResponseUserDto;
import tpi.backend.e_commerce.models.User;

public class UserMapper {
    
    public static ResponseUserDto toDto(User user){
        return ResponseUserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .creationDatetime(user.getCreationDatetime())
            .updateDateTime(user.getUpdateDateTime())
            .deleteDateTime(user.getDeleteDateTime())
            .build();
    }
}
