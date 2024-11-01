package tpi.backend.e_commerce.dto.auth.request;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tpi.backend.e_commerce.validation.customValidators.AgeRange;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

    @NotBlank(message = "No puede estar vacio")
    private String firstName;

    @NotBlank(message = "No puede estar vacio")
    private String lastName;

    @Past(message = "No cumple con la edad para registrarse en el sitio")
    @AgeRange(message = "La edad debe estar entre 18 y 100 años")
    private Date dateBirth;
}
