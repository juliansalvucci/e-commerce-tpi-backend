package tpi.backend.e_commerce.mapper;

import java.util.List;

import tpi.backend.e_commerce.dto.orderDetailDto.CreateOrderDetailDto;
import tpi.backend.e_commerce.dto.orderDetailDto.ResponseOrderDetailDto;
import tpi.backend.e_commerce.models.Order;
import tpi.backend.e_commerce.models.OrderDetail;
import tpi.backend.e_commerce.models.Product;

public class OrderDetailMapper {
    
    public static OrderDetail toEntity(CreateOrderDetailDto orderDetailDto, Order order, Product product){
        
        return OrderDetail
            .builder()
            .amount(orderDetailDto.getAmount())
            .order(order)
            .product(product)
            .build();
    }

    public static ResponseOrderDetailDto toDto(OrderDetail orderDetail){
        return ResponseOrderDetailDto
            .builder()
            .productName(orderDetail.getProduct().getName())
            .unitPrice(orderDetail.getProduct().getPrice())
            .subTotal(orderDetail.getSubTotal())
            .amount(orderDetail.getAmount())
            .build();
    }

    public static List<ResponseOrderDetailDto> toDtoList(List<OrderDetail> orderDetails){
        return orderDetails
            .stream()
            .map(orderDetail -> OrderDetailMapper.toDto(orderDetail))
            .toList();
    }
}
