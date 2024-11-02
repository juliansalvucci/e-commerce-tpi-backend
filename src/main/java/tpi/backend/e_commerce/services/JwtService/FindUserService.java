package tpi.backend.e_commerce.services.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import tpi.backend.e_commerce.mapper.UserMapper;
import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.JwtService.interfaces.IFindUserService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class FindUserService implements IFindUserService{

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private Validation validation;
    
    @Override
    public ResponseEntity<?> findAllActive() {
        return ResponseEntity.ok(UserMapper.toDtoList(userRepository.findAllActive()));
    }

    @Override
    public ResponseEntity<?> findAllDeleted() {
        return ResponseEntity.ok(UserMapper.toDtoList(userRepository.findAllDeleted()));
    }

    @Override
    public ResponseEntity<?> findActiveById(Long id) {
        Optional<User> optionalUser = userRepository.findActiveById(id); 
        if (optionalUser.isEmpty()){
            return validation.validate(
                "id",
                "No existe un usuario con ese id",
                404
            );
        }
        return ResponseEntity.ok(UserMapper.toDto(optionalUser.get()));
    }
    
}
