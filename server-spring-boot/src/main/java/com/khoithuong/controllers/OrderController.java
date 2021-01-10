package com.khoithuong.controllers;

import com.khoithuong.entity.Order;
import com.khoithuong.exception.ResourceNotFoundException;
import com.khoithuong.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders")
    public Order createCartItem(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") Integer orderId,
                                                   @RequestBody Order orderDetails) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        order.setListCartItem(orderDetails.getListCartItem());
        order.setAmount(orderDetails.getAmount());

        final Order updatedOrder = orderRepository.save(order);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/orders/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Integer orderId)
            throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        orderRepository.delete(order);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
