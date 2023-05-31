package com.cts.bookShop.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User user;
    @OneToOne
    private Cart cart;

    private Double amount;

    public OrderData(User user, Cart cart, Double amount) {
        this.user = user;
        this.cart = cart;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", cart=" + cart +
                ", amount=" + amount +
                '}';
    }
}
