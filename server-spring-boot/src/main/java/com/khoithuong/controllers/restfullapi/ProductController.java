package com.khoithuong.controllers.restfullapi;

import com.khoithuong.entity.Product;
import com.khoithuong.exception.ResourceNotFoundException;
import com.khoithuong.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Integer proId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(proId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + proId));
        return ResponseEntity.ok().body(product);
    }
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Integer proId,
                                               @RequestBody Product proDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(proId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + proId));

        product.setName(proDetails.getName());
        product.setPrice(proDetails.getPrice());
        product.setSalePrice(proDetails.getSalePrice());
        product.setDiscount(proDetails.getDiscount());
        product.setPictures(proDetails.getPictures());
        product.setShortDetails(proDetails.getShortDetails());
        product.setDescription(proDetails.getDescription());
        product.setStock(proDetails.getStock());
        product.setBrand(proDetails.getBrand());
        product.setNewPro(proDetails.isNewPro());
        product.setSale(proDetails.isSale());
        product.setState(proDetails.getState());
        product.setCategory(proDetails.getCategory());

        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Integer proId)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(proId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + proId));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

//    @PostMapping("/do-search")
//    public List<Product> searchProduct(@RequestBody Product product){
//        List<Product> listPro = new ArrayList<>();
//        listPro = productRepository.searchProduct(product);
//        return listPro;
//    }
}
