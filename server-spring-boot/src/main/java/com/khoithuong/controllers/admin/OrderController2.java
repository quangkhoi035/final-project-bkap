package com.khoithuong.controllers.admin;

import com.khoithuong.entity.Order;
import com.khoithuong.entity.Product;
import com.khoithuong.entity.Role;
import com.khoithuong.entity.User;
import com.khoithuong.exception.ResourceNotFoundException;
import com.khoithuong.repository.CategoryRepository;
import com.khoithuong.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController2 {
    @Autowired
    private OrderRepository orderRepository;
    @RequestMapping("")
    public String OrderHome(Model model) {
        List<Order> listOrder = orderRepository.findAll();
        model.addAttribute("listOrder", listOrder);
        return "order/order-home-page";
    }

    @RequestMapping("/update/{id}")
    public String InitUpdateUser(Model model,
                                 @PathVariable(value = "id") Integer id)throws ResourceNotFoundException {
        Order updateOrder = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        model.addAttribute("updateOrder", updateOrder);
        return "order/init-update";
    }

    @PostMapping("/update-order")
    public String updateUser(@ModelAttribute("updateOrder") Order updateOrder) throws ResourceNotFoundException {
        orderRepository.save(updateOrder);
        return "redirect:/order";
    }
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        orderRepository.delete(order);
        return "redirect:/order";
    }
}
