package com.khoithuong.controllers;

import com.khoithuong.entity.CartItem;
import com.khoithuong.entity.Category;
import com.khoithuong.entity.Product;
import com.khoithuong.exception.ResourceNotFoundException;
import com.khoithuong.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/cart-items")
    public CartItem createCartItem(@RequestBody CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @PutMapping("/cart-items/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable(value = "id") Integer cartId,
                                                   @RequestBody CartItem cartItemDetails) throws ResourceNotFoundException {
        CartItem cartItem = cartItemRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found for this id :: " + cartId));

        cartItem.setProduct(cartItemDetails.getProduct());
        cartItem.setQuantity(cartItemDetails.getQuantity());

        final CartItem updatedCartItem = cartItemRepository.save(cartItem);
        return ResponseEntity.ok(updatedCartItem);
    }

    @DeleteMapping("/cart-items/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Integer cartId)
            throws ResourceNotFoundException {
        CartItem cartItem = cartItemRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found for this id :: " + cartId));

        cartItemRepository.delete(cartItem);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
