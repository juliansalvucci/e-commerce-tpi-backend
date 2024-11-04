package tpi.backend.e_commerce.UserTests.UnitTests;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.auth.request.SignUpRequest;
import tpi.backend.e_commerce.dto.auth.response.JwtAuthenticationResponse;
import tpi.backend.e_commerce.enums.Role;
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
        //Edad mayor a 18 años
        LocalDate validBirthDate = LocalDate.now().minusYears(25);

        // Crear un request con un email duplicado
        SignUpRequest request = SignUpRequest.builder()
                .firstName("Julián")
                .lastName("Salvucci")
                .dateBirth(validBirthDate) 
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
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode(), "Correo ya registrado");

        // Verificar que el cuerpo de la respuesta contenga el mensaje esperado
        assertEquals(Collections.singletonMap("email", "Ya existe un usuario con ese email"), response.getBody());
    }

    @SuppressWarnings("null")
    @Test
    void testNotDuplicateEmail() {
        //Edad mayor a 18 años
        LocalDate validBirthDate = LocalDate.now().minusYears(25);

        // Crear un request con un email único
        SignUpRequest request = SignUpRequest.builder()
                .firstName("Julián")
                .lastName("Salvucci")
                .email("test@example.com")
                .dateBirth(validBirthDate) 
                .password("securePassword123")
                .build();

        // Simular que el email no existe en la base de datos
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);

        // Llamar al servicio de autenticación
        ResponseEntity<?> response = authenticationService.signup(request, result);

        // Verificar que el estado HTTP sea 200 OK 
        assertEquals(HttpStatus.OK, response.getStatusCode(), "Correo válido");

        // Verificar que el cuerpo de la respuesta contenga los detalles del usuario
        // registrado
        JwtAuthenticationResponse expectedResponse = JwtAuthenticationResponse.builder()
                .firstName("Julián")
                .lastName("Salvucci")
                .email("test@example.com")
                .role(Role.USER)
                .token(any(String.class))
                .build();

        assertEquals(expectedResponse.getFirstName(), ((JwtAuthenticationResponse) response.getBody()).getFirstName());
        assertEquals(expectedResponse.getLastName(), ((JwtAuthenticationResponse) response.getBody()).getLastName());
        assertEquals(expectedResponse.getEmail(), ((JwtAuthenticationResponse) response.getBody()).getEmail());
        assertEquals(expectedResponse.getRole(), ((JwtAuthenticationResponse) response.getBody()).getRole());
    }
}
