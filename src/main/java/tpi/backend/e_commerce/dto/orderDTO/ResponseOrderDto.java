package tpi.backend.e_commerce.dto.orderDTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import tpi.backend.e_commerce.dto.orderDetailDto.ResponseOrderDetailDto;

@Data
@AllArgsConstructor
@Builder
public class ResponseOrderDto {
    
    private Long id;
    private String userEmail;
    private Double total;
    private List<ResponseOrderDetailDto> orderDetails;
    private LocalDateTime creationDatetime;
}
