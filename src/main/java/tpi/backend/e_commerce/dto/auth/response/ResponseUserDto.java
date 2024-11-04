package tpi.backend.e_commerce.dto.auth.response;

import tpi.backend.e_commerce.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private LocalDate dateBirth;
    private LocalDateTime creationDatetime;
    private LocalDateTime updateDateTime;
    private LocalDateTime deleteDateTime;
    
}
