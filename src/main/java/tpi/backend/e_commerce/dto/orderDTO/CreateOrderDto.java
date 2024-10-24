package tpi.backend.e_commerce.dto.orderDTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tpi.backend.e_commerce.dto.orderDetailDto.CreateOrderDetailDto;

@Data
@Builder
@AllArgsConstructor
public class CreateOrderDto {
    
    private String userEmail;

    private List<CreateOrderDetailDto> orderDetails;

}
