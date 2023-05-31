package com.cts.bookShop.dao;

import com.cts.bookShop.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<OrderData,Integer> {
}
