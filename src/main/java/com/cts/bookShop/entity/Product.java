package com.cts.bookShop.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pId;

    @Column(name = "isbn")
    private String ISBN;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "imgurl")
    private String imgUrl;

    @Column(name = "category")
    private String category;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;


}
