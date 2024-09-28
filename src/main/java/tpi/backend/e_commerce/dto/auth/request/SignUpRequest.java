package tpi.backend.e_commerce.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "No puede estar vacio")
    private String firstName;

    @NotBlank(message = "No puede estar vacio")
    private String lastName;

    @Email(message = "No respeta el formato de email")
    @NotBlank(message = "No puede estar vacio")
    private String email;

    @NotBlank(message = "No puede estar vacio")
    @Size(min = 8, max = 22, message = "Debe tener entre 8 y 22 caracteres")
    private String password;
    
}
