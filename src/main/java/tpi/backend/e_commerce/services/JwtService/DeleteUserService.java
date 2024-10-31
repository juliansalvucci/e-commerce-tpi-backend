package tpi.backend.e_commerce.services.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import tpi.backend.e_commerce.mapper.UserMapper;
import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.JwtService.interfaces.IDeleteUserService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class DeleteUserService implements IDeleteUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private Validation validation;
    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            return validation.validate(
                "id",
                "No existe un usuario con ese id",
                404
            );
        }
        User user = optionalUser.get();
        user.setDeleted(true);
        userRepository.save(user);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<?> recover(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            return validation.validate(
                "id",
                "No existe un usuario con ese id",
                404
            );
        }
        User user = optionalUser.get();
        user.setDeleted(false);
        userRepository.save(user);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }
    
}
