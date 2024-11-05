package tpi.backend.e_commerce.dto.auth.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private String token;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private LocalDate birthDate;
    private LocalDateTime creationDatetime;
    private LocalDateTime updateDateTime;
    private LocalDateTime deleteDateTime;
}
