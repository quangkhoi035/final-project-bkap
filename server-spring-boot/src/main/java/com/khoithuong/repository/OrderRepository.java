package com.khoithuong.repository;

import com.khoithuong.entity.Order;
import com.khoithuong.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findOrdersByUser(User user);
}
