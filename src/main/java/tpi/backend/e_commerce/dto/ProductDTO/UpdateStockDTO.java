package tpi.backend.e_commerce.dto.ProductDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateStockDTO {
    @Min(0)
    @NotNull(message = "Debe ingresar un stock")
    private Long stock;
}
