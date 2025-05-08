package tpi.backend.e_commerce.UserTests.UnitTests;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.auth.request.SignInRequest;
import tpi.backend.e_commerce.dto.auth.response.JwtAuthenticationResponse;
import tpi.backend.e_commerce.enums.Role;
import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.JwtService.AuthenticationService;
import tpi.backend.e_commerce.services.JwtService.JwtService;
import tpi.backend.e_commerce.validation.Validation;
import java.util.Map;
import java.util.Collections;

@SpringBootTest
public class PruebaN05 {
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
                // Setup
                SignInRequest request = SignInRequest.builder()
                                .email("test@example.com")
                                .password("wrongPassword123")
                                .build();

                BindingResult result = mock(BindingResult.class);
                when(result.hasFieldErrors()).thenReturn(false);

                User mockUser = User.builder()
                                .email("test@example.com")
                                .password("encrypted") // no importa en este test
                                .role(Role.USER)
                                .build();
                when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

                doThrow(new AuthenticationException("Bad credentials") {
                })
                                .when(authenticationManager)
                                .authenticate(any(UsernamePasswordAuthenticationToken.class));

                Map<String, String> error = Collections.singletonMap("password", "La contraseña es incorrecta");
                when(validation.validate("password", "La contraseña es incorrecta", 401))
                                .thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error));

                // Execute
                ResponseEntity<?> response = authenticationService.signin(request, result);

                // Assert
                assertNull(response);

        }

        @Test
        void testCorrectPasswordOnSignIn() {
                // Crear la solicitud con email y contraseña correctos
                SignInRequest request = SignInRequest.builder()
                                .email("test@example.com")
                                .password("correctPassword123")
                                .build();

                // Crear el usuario simulado
                User mockUser = User.builder()
                                .email("test@example.com")
                                .password(passwordEncoder.encode("correctPassword123")) // esta será codificada
                                .role(Role.USER)
                                .build();

                // Simular búsqueda del usuario en el repositorio
                when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(mockUser));

                // Simular autenticación exitosa
                when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                                .thenReturn(new UsernamePasswordAuthenticationToken(request.getEmail(),
                                                request.getPassword()));

                // Simular generación de JWT
                String mockToken = "mock-jwt-token";
                when(jwtService.generateToken(mockUser)).thenReturn(mockToken);

                // Construir la respuesta esperada
                JwtAuthenticationResponse expectedResponse = JwtAuthenticationResponse.builder()
                                .token(mockToken)
                                .email("test@example.com")
                                .role(Role.USER)
                                .build();

                // Ejecutar el método signin
                ResponseEntity<?> response = authenticationService.signin(request, result);

                // Verificar el código de estado
                assertEquals(HttpStatus.OK, response.getStatusCode());

                // Verificar que el cuerpo de la respuesta coincide con lo esperado
                assertEquals(expectedResponse, response.getBody());
        }
}
