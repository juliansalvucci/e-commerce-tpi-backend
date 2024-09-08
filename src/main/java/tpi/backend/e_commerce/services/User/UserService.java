package tpi.backend.e_commerce.services.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IUserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}