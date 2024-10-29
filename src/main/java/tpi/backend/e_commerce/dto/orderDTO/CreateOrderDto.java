package tpi.backend.e_commerce.dto.orderDTO;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tpi.backend.e_commerce.dto.orderDetailDto.CreateOrderDetailDto;

@Data
@Builder
@AllArgsConstructor
public class CreateOrderDto {
    
    private String userEmail;

    @Valid
    @NotNull(message = "Debe ingresar los detalles de la orden")
    private List<CreateOrderDetailDto> orderDetails;

}
