package tpi.backend.e_commerce.UserTests.UnitTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import tpi.backend.e_commerce.services.JwtService.JwtService;

@SpringBootTest
public class JwtTokenAvailabilityNonFunctionalTest {

    @Autowired
    private JwtService jwtService;

    @Test
    public void testTokenAvailabilityOverTime() {
        // Simula un usuario
        UserDetails userDetails = User.builder()
                .username("testuser@example.com")
                .password("password")
                .roles("USER")
                .build();

        // Configuración de la prueba
        long testDurationMillis = 1000 * 60 * 2; // 2 minutos
        long startTime = System.currentTimeMillis();
        int tokenCount = 0;

        while (System.currentTimeMillis() - startTime < testDurationMillis) {
            // Genera un token
            String token = jwtService.generateToken(userDetails);

            // Valida el token
            boolean isValid = jwtService.isTokenValid(token, userDetails);
            assertTrue(isValid, "El token debería ser válido.");

            tokenCount++;
        }

        System.out.println("Prueba completada. Tokens generados y validados: " + tokenCount);
    }
}
