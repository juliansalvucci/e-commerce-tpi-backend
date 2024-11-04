package tpi.backend.e_commerce.mapper;


import java.util.List;

import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;
import tpi.backend.e_commerce.dto.auth.request.UpdateUserDto;
import tpi.backend.e_commerce.dto.auth.response.JwtAuthenticationResponse;
import tpi.backend.e_commerce.dto.auth.response.ResponseUserDto;
import tpi.backend.e_commerce.enums.Role;
import tpi.backend.e_commerce.models.User;

public class UserMapper {
    
    //Unicamente se actualiza el nombre, apellido y fecha de nacimiento.
    //El resto de datos del usuario se obtienen del objeto usuario de la bd
    public static User toUpdateEntity(UpdateUserDto userDto, User user){
        return User
            .builder()
            .id(user.getId())
            .firstName(userDto.getFirstName())
            .lastName(userDto.getLastName())
            .email(user.getEmail())
            .dateBirth(userDto.getDateBirth())
            .password(user.getPassword())
            .role(user.getRole())
            .creationDatetime(user.getCreationDatetime())
            .build();
    }
    
    public static User toEntity(SignUpRequest userDto, String password, Role role){
        return User
            .builder()
            .firstName(userDto.getFirstName())
            .lastName(userDto.getLastName())
            .email(userDto.getEmail())
            .password(password)
            .role(role)
            .dateBirth(userDto.getDateBirth())
            .build();
    }

    public static ResponseUserDto toDto(User user){
        return ResponseUserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .dateBirth(user.getDateBirth())
            .creationDatetime(user.getCreationDatetime())
            .updateDateTime(user.getUpdateDateTime())
            .deleteDateTime(user.getDeleteDateTime())
            .build();
    }

    public static JwtAuthenticationResponse toJwtDto(User user, String jwt){
        return JwtAuthenticationResponse.builder()
            .token(jwt)
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .role(user.getRole())
            .birthDate(user.getDateBirth())
            .creationDatetime(user.getCreationDatetime())
            .updateDateTime(user.getUpdateDateTime())
            .deleteDateTime(user.getDeleteDateTime())
            .build();
    } 

    public static List<ResponseUserDto> toDtoList(List<User> users){
        return users
            .stream()
            .map(UserMapper::toDto)
            .toList();
    }
}
