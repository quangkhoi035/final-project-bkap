package com.khoithuong.controllers;

import com.khoithuong.entity.CartItem;
import com.khoithuong.entity.Category;
import com.khoithuong.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class CartItemController {
    @Autowired
    private CartItemRepository cartItemRepository;

    @GetMapping("/cart-items")
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

}
