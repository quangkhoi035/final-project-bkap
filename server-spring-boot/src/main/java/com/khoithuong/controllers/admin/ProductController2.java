package com.khoithuong.controllers.admin;

import com.khoithuong.entity.Category;
import com.khoithuong.entity.Product;
import com.khoithuong.exception.ResourceNotFoundException;
import com.khoithuong.repository.CategoryRepository;
import com.khoithuong.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController2 {

    private final String UPLOAD_DIR = "C:\\Users\\Tran Quang Khoi\\Desktop\\sophia\\food-demo\\src\\assets\\images\\products\\";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("")
    public String ProductHome(Model model) {
        List<Product> listPro = productRepository.findAll();
        model.addAttribute("listPro", listPro);
        return "product/product-home-page";
    }

    @RequestMapping("/init-insert")
    public String InitInsertUser(Model model) {
        model.addAttribute("newPro", new Product());
        model.addAttribute("listCat", categoryRepository.findAll());
        return "product/init-insert";
    }

    @PostMapping("/insert-product")
    public String addUser(@ModelAttribute("newUser") Product newPro,
                          @RequestParam("catResult") int catId,
                          @RequestParam("file") MultipartFile file) throws ResourceNotFoundException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        newPro.setState("small");
        Category cat = categoryRepository.findById(catId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + catId));
        newPro.setCategory(cat);
        newPro.setPictures( "assets/images/products/" + fileName);
        productRepository.save(newPro);
        return "redirect:/product";
    }

    @RequestMapping("/delete/{id}")
    public String deletePro(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        productRepository.delete(product);
        return "redirect:/product";
    }
}