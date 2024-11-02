package tpi.backend.e_commerce.UserTests;

import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
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
public class TestDontAceptDuplicateEmail {
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);
        when(validation.validate(anyString(), anyString(), anyInt()))
                .thenReturn(ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Collections.singletonMap("email", "Ya existe un usuario con ese email")));
    }

    @Test
    void testDuplicateEmail() {
        SignUpRequest request = SignUpRequest.builder()
            .firstName("Julián")
            .lastName("Salvucci")
            .dateBirth(new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 365 * 20)) // Edad mayor de 18 años
            .email("jls@mail.com") // Email duplicado
            .password("micontraseña")
            .build();

        ResponseEntity<?> response = authenticationService.signup(request, result);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode(), "Debe retornar un 409 si el email ya existe");
        assertEquals(Collections.singletonMap("email", "Ya existe un usuario con ese email"), response.getBody(), "El mensaje de error no coincide");
    }
}
