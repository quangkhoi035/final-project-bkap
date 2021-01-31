package com.khoithuong.controllers.restfullapi;

import com.khoithuong.entity.Order;
import com.khoithuong.entity.User;
import com.khoithuong.exception.ResourceNotFoundException;
import com.khoithuong.repository.CartItemRepository;
import com.khoithuong.repository.OrderRepository;
import com.khoithuong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping("/orders/find-by-user")
    public List<Order> getOrdersByUserId(@RequestBody User user) {
        System.err.println("Day la get Order");
        return orderRepository.findOrdersByUser(user);
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) {
        order.getListCartItem().forEach(cartItem -> cartItem.setOrder(order));
        order.getAddress().setOrder(order);
        order.setState("Pending");
        if (order.getUser() == null){
            order.setUser(userRepository.findById(new Long(9)).get());
        }
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
