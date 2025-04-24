package tpi.backend.e_commerce.UserTests.UnitTests;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.auth.request.SignInRequest;
import tpi.backend.e_commerce.enums.Role;
import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.JwtService.AuthenticationService;
import tpi.backend.e_commerce.services.JwtService.JwtService;
import tpi.backend.e_commerce.validation.Validation;

@SpringBootTest
public class TestIncorrectPassword {
    @Mock
    private IUserRepository userRepository;

    @Mock
    private Validation validation;

    @Mock
    private BindingResult result;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void testIncorrectPasswordOnSignIn() {
        // Crear una solicitud de inicio de sesión con email válido pero contraseña
        // incorrecta
        SignInRequest request = SignInRequest.builder()
                .email("test@example.com")
                .password("wrongPassword123") // Contraseña incorrecta
                .build();

        // Simular que el usuario existe en la base de datos
        User mockUser = User.builder()
                .email("test@example.com")
                .password(passwordEncoder.encode("correctPassword123")) // Contraseña correcta en BD
                .role(Role.USER)
                .build();

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

        // Simular que el AuthenticationManager lanza una excepción cuando la contraseña
        // es incorrecta
        doThrow(new AuthenticationException("Bad credentials") {
        })
                .when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));

        // Simular la respuesta de validación para "contraseña incorrecta"
        when(validation.validate("password", "La contraseña es incorrecta", 401))
                .thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("password", "La contraseña es incorrecta")));

        // Llamar al método signin
        ResponseEntity<?> response = authenticationService.signin(request, result);

        // Verificar que el estado HTTP sea 401 UNAUTHORIZED
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());

        // Verificar que el mensaje de error sea el esperado
        assertEquals(Collections.singletonMap("password", "La contraseña es incorrecta"), response.getBody());
    }

}
