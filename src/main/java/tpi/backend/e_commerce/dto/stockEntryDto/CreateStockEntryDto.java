package tpi.backend.e_commerce.dto.stockEntryDto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import tpi.backend.e_commerce.dto.stockEntryDetailDto.CreateStockEntryDetailDto;

@Data
public class CreateStockEntryDto {

    @Valid
    @NotNull(message = "Debe ingresar los detalles de la entrada de stock")
    private List<CreateStockEntryDetailDto> stockEntryDetails;
    
}
