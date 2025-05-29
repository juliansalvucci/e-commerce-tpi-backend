package tpi.backend.e_commerce.categoryTests.securityTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import tpi.backend.e_commerce.services.JwtService.interfaces.IAuthenticationService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class categorySecurityTest1 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAuthenticationService authenticationService;

    @Test
    void shouldRejectAccessWithoutJwt() throws Exception {
        mockMvc.perform(get("/category"))
                .andExpect(status().isForbidden());
    }
}
