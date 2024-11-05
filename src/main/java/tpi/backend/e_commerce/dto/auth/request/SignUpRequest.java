package tpi.backend.e_commerce.dto.auth.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tpi.backend.e_commerce.validation.customValidators.AgeRange;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    @NotBlank(message = "No puede estar vacio")
    private String firstName;

    @NotBlank(message = "No puede estar vacio")
    private String lastName;

    @Past(message = "No cumple con la edad para registrarse en el sitio")
    @AgeRange(message = "La edad debe estar entre 18 y 100 años")
    private LocalDate dateBirth; //"yyyy-MM-dd"

    @Email(message = "No respeta el formato de email")
    @NotBlank(message = "No puede estar vacio")
    private String email;

    @NotBlank(message = "No puede estar vacio")
    @Size(min = 8, max = 22, message = "Debe tener entre 8 y 22 caracteres")
    private String password;

}
