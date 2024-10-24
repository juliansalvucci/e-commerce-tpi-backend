package tpi.backend.e_commerce.dto.orderDetailDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateOrderDetailDto {
    
    private Long productId;
    private Integer amount;

}
