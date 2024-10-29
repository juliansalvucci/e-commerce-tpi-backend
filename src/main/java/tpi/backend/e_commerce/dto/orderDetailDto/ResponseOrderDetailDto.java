package tpi.backend.e_commerce.dto.orderDetailDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseOrderDetailDto {
    
    private String productName;
    private Double unitPrice;
    private Integer amount;
    private Double subTotal;
}
