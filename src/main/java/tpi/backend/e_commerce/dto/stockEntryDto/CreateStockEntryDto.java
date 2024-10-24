package tpi.backend.e_commerce.dto.stockEntryDto;

import java.util.List;

import lombok.Data;
import tpi.backend.e_commerce.dto.stockEntryDetailDto.CreateStockEntryDetailDto;

@Data
public class CreateStockEntryDto {

    private List<CreateStockEntryDetailDto> stockEntryDetailsDto;
    
}
