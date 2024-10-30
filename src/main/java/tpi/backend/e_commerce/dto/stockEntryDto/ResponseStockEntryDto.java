package tpi.backend.e_commerce.dto.stockEntryDto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tpi.backend.e_commerce.dto.stockEntryDetailDto.ResponseStockEntryDetailDto;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ResponseStockEntryDto {

    private Long id;
    private LocalDateTime creationDatetime;
    private List<ResponseStockEntryDetailDto> stockEntryDetails;

}
