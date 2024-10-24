package tpi.backend.e_commerce.mapper;


import tpi.backend.e_commerce.dto.orderDTO.ResponseOrderDto;
import tpi.backend.e_commerce.models.Order;
import tpi.backend.e_commerce.models.User;

public class OrderMapper {
    
    public static Order toEntity(User user){
        return Order
            .builder()
            .user(user)
            .build();
    }

    public static ResponseOrderDto toDto(Order order){
        return ResponseOrderDto
            .builder()
            .id(order.getId())
            .userEmail(order.getUser().getEmail())
            .total(order.getTotal())
            .orderDetails(OrderDetailMapper.toDtoList(order.getOrderDetails()))
            .build();
    }
}
