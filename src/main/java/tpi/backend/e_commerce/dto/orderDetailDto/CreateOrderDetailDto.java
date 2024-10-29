package tpi.backend.e_commerce.dto.orderDetailDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateOrderDetailDto {
    @NotNull(message = "Debe ingresar un productId")
    private Long productId;
    @NotNull(message = "Debe ingresar la cantidad de unidades del producto")
    @Min(1)
    private Integer amount;

}
