package tpi.backend.e_commerce.dto.auth.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tpi.backend.e_commerce.enums.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse { //ResponseUserDto

    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String token;
}
