package tpi.backend.e_commerce.services.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.orderDTO.CreateOrderDto;
import tpi.backend.e_commerce.dto.orderDetailDto.CreateOrderDetailDto;
import tpi.backend.e_commerce.mapper.OrderDetailMapper;
import tpi.backend.e_commerce.mapper.OrderMapper;
import tpi.backend.e_commerce.models.Order;
import tpi.backend.e_commerce.models.OrderDetail;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.models.User;
import tpi.backend.e_commerce.repositories.IOrderRepository;
import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.repositories.IUserRepository;
import tpi.backend.e_commerce.services.order.interfaces.ISaveOrderService;
import tpi.backend.e_commerce.services.product.interfaces.IModifyProductService;
import tpi.backend.e_commerce.validation.Validation;

@Service
public class SaveOrderService implements ISaveOrderService{

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private Validation validation;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IModifyProductService productService;

    @Override
    public ResponseEntity<?> create(CreateOrderDto orderDto, BindingResult result) {

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }

        Optional<User> optionalUser = userRepository.findByEmail(orderDto.getUserEmail());
        if (optionalUser.isEmpty()){
            System.out.println(orderDto.getUserEmail());
            return validation.validate(
                "userEmail",
                "No existe un usuario con ese email en la base de datos",
                404
            );
        }
        
        //Valido que los productos existan en la base de datos. De no existir devuelvo el error
        try {
            validateProducts(orderDto);
        } catch (RuntimeException e) {
            return validation.validate(
                "productId",
                e.getMessage(),
                404  
                );
        }

        //Guardo la orden en la bd asignada a un usuario
        Order order = orderRepository.save(OrderMapper.toEntity(optionalUser.get()));

        Order orderToSave = createOrderDetails(orderDto.getOrderDetails(), order);

        return ResponseEntity.status(HttpStatus.CREATED).body(OrderMapper.toDto(orderRepository.save(orderToSave)));

    }   

    private void validateProducts(CreateOrderDto orderDto) throws RuntimeException{
        orderDto.getOrderDetails().forEach(orderDetailDto -> {

            if (!productRepository.existsById(orderDetailDto.getProductId())) {
                throw new RuntimeException("No existe un producto con id " + orderDetailDto.getProductId() + " en la base de datos");
            }
        });    
    }

    private Order createOrderDetails(List<CreateOrderDetailDto> orderDetailsDto , Order order) {

        List<OrderDetail> orderDetailListToSave = new ArrayList<>();

        orderDetailsDto.forEach(orderDetailDto -> {

            Optional<Product> optionalProduct = productRepository.findById(orderDetailDto.getProductId());
            Product product = optionalProduct.get();

            //Le pido al servicio de producto que me descuente el stock
            product = productService.discountStock(product, orderDetailDto.getAmount());

            OrderDetail orderDetailToSave = OrderDetailMapper.toEntity(orderDetailDto,order,product);

            orderDetailListToSave.add(orderDetailToSave);
        });

        order.setOrderDetails(orderDetailListToSave);
        return order;
    }

}
