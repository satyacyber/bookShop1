package com.cts.bookShop.dao;


import com.cts.bookShop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer> {

}
