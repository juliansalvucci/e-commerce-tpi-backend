package tpi.backend.e_commerce.services.JwtService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import tpi.backend.e_commerce.dto.auth.request.SignInRequest;
import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;
import tpi.backend.e_commerce.dto.auth.response.JwtAuthenticationResponse;
import tpi.backend.e_commerce.enums.Role;
import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.JwtService.interfaces.IAuthenticationService;
import tpi.backend.e_commerce.validation.Validation;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final Validation validation;

    @Override
    public ResponseEntity<?> signup(SignUpRequest request, BindingResult result) {

        // Si hay algun error de validacion, retornara un 400 con los errores
        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        // Chequea que el email no exista en la BD
        if (userRepository.existsByEmail(request.getEmail())) {
            return validation.validate(
                    "email",
                    "Ya existe un usuario con ese email",
                    409);
        }

        // Si no hay errores, guarda al usuario en la BD y retorna el JWT
        var user = User.builder().firstName(request.getFirstName()).lastName(request.getLastName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return ResponseEntity
                .ok(JwtAuthenticationResponse.builder().token(jwt).firstName(user.getFirstName())
                        .lastName(user.getLastName()).email(user.getEmail()).role(user.getRole()).build());
    }

    @Override
    public ResponseEntity<?> signin(SignInRequest request, BindingResult result) {

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            return validation.validate(
                    "email",
                    "No existe un usuario con ese email",
                    404);
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

        } catch (AuthenticationException e) {
            return validation.validate(
                    "password",
                    "La contraseña es incorrecta",
                    401);
        }

        var jwt = jwtService.generateToken(optionalUser.get());
        var user = optionalUser.get();
        return ResponseEntity.ok(JwtAuthenticationResponse.builder().token(jwt).firstName(user.getFirstName())
                .lastName(user.getLastName()).email(user.getEmail()).role(user.getRole()).build());
    }
}
