package tpi.backend.e_commerce.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;
    private String discount;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    private LocalDateTime creation_datetime;
    private LocalDateTime delete_datetime;

    @PrePersist
    public void prePersist(){
        creation_datetime = LocalDateTime.now();
    }

    @PreUpdate
    public void PreUpdate(){
        calculateTotal();
    }

    public void calculateTotal(){
        orderDetails.forEach(orderDetail -> {
            total+=orderDetail.getSubTotal();
        });
        calculateDiscount();
    }

    public void calculateDiscount(){
        if (total>1000000) {
            total = total*0.95;
            discount = "5%";
        }else{
            discount = "0%";
        }
    }

}
