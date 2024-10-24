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
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double total;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    private boolean deleted;

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
    }

}
