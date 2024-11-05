package tpi.backend.e_commerce.mapper;


import java.util.List;

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
            .discount(order.getDiscount())
            .orderDetails(OrderDetailMapper.toDtoList(order.getOrderDetails()))
            .creationDatetime(order.getCreation_datetime())
            .build();
    }

    public static List<ResponseOrderDto> toDtoList(List<Order> orders){
        return orders
            .stream()
            .map(order -> OrderMapper.toDto(order))
            .toList();
    }
}
