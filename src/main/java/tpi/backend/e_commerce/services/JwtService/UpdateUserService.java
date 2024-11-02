package tpi.backend.e_commerce.services.JwtService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.auth.request.UpdateUserDto;
import tpi.backend.e_commerce.mapper.UserMapper;
import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.JwtService.interfaces.IUpdateUserService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class UpdateUserService implements IUpdateUserService{

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private Validation validation;

    @Override
    public ResponseEntity<?> update(Long id, UpdateUserDto userDto, BindingResult result) {

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        Optional<User> optionalUser = userRepository.findActiveById(id);
        if (optionalUser.isEmpty()) {
            return validation.validate(
                "id",
                "El id no corresponde a ningun usuario",
                404
            );
        }

        User userToSave = UserMapper.toUpdateEntity(userDto, optionalUser.get());
        return ResponseEntity.ok(UserMapper.toDto(userRepository.save(userToSave)));
    }
    
}
