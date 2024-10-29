package tpi.backend.e_commerce.dto.stockEntryDetailDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseStockEntryDetailDto {
    
    private Long id;
    private Long productId;
    private Integer quantity;
}
