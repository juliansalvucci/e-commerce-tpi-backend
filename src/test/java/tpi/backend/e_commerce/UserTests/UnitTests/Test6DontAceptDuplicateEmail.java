package tpi.backend.e_commerce.UserTests.UnitTests;

import java.util.Collections;
import java.util.Date;

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

import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.JwtService.AuthenticationService;
import tpi.backend.e_commerce.services.JwtService.JwtService;
import tpi.backend.e_commerce.validation.Validation;

@SpringBootTest
public class Test6DontAceptDuplicateEmail {
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
    void testDuplicateEmail() {
        // Crear un request con un email duplicado
        SignUpRequest request = SignUpRequest.builder()
            .firstName("Julián")
            .lastName("Salvucci")
            .dateBirth(new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 365 * 20)) // Edad mayor de 18 años
            .email("test@example.com") // Email duplicado
            .password("securePassword123")
            .build();
    
        // Simular que el email ya existe en la base de datos
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);
    
        // Simular la respuesta de validación de email duplicado
        when(validation.validate("email", "Ya existe un usuario con ese email", 409))
            .thenReturn(ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap("email", "Ya existe un usuario con ese email")));
    
        // Llamar al servicio de autenticación
        ResponseEntity<?> response = authenticationService.signup(request, result);
    
        // Verificar que el estado HTTP sea 409 CONFLICT
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode(), "Debe retornar un 409 si el email ya existe");
    
        // Verificar que el cuerpo de la respuesta contenga el mensaje esperado
        assertEquals(Collections.singletonMap("email", "Ya existe un usuario con ese email"), response.getBody());
    }
    
}
