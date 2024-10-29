package tpi.backend.e_commerce.dto.stockEntryDetailDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateStockEntryDetailDto {
    @NotNull(message = "Debe ingresar un productId")
    private Long productId;
    @NotNull(message = "Debe ingresar la cantidad de unidades del producto")
    @Min(1)
    private Integer quantity;
}
