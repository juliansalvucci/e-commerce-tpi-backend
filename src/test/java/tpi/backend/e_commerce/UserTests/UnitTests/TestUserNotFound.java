package tpi.backend.e_commerce.UserTests.UnitTests;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.auth.request.SignInRequest;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.JwtService.AuthenticationService;
import tpi.backend.e_commerce.services.JwtService.JwtService;
import tpi.backend.e_commerce.validation.Validation;

@SpringBootTest
public class TestUserNotFound {
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

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void testUserNotFoundOnSignIn() {
        // Crear un request con un email que no está registrado
        SignInRequest request = SignInRequest.builder()
                .email("nonexistent@example.com")
                .password("somePassword123")
                .build();

        // Simular que el email no existe en la base de datos
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());

        // Simular la respuesta de validación de usuario no encontrado
        when(validation.validate("email", "No existe un usuario con ese email", 404))
                .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("email", "No existe un usuario con ese email")));

        // Llamar al servicio de autenticación
        ResponseEntity<?> response = authenticationService.signin(request, result);

        // Verificar que el estado HTTP sea 404 NOT FOUND
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "Usuario no encontrado");

        // Verificar que el cuerpo de la respuesta contenga el mensaje esperado
        assertEquals(Collections.singletonMap("email", "No existe un usuario con ese email"), response.getBody());
    }
}
