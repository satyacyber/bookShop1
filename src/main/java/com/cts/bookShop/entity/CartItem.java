package com.cts.bookShop.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@NoArgsConstructor
public class CartItem {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Integer id;

    @OneToOne
    Product product;



    Integer cust_quantity;

    public CartItem(Product product, Integer cust_quantity) {
        this.product = product;
        this.cust_quantity = cust_quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", cust_quantity=" + cust_quantity +
                '}';
    }
}
