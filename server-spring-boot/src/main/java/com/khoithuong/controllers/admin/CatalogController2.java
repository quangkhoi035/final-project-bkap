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
@RequestMapping("/catalog")
public class CatalogController2 {
    private final String UPLOAD_DIR = "C:\\Users\\Tran Quang Khoi\\Desktop\\sophia\\food-demo\\src\\assets\\images\\categories\\";

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("")
    public String CategoryHome(Model model) {
        List<Category> listCat = categoryRepository.findAll();
        model.addAttribute("listCat", listCat);
        return "catalog/catalog-home-page";
    }

    @RequestMapping("/init-insert")
    public String InitInsertUser(Model model) {
        model.addAttribute("newCat", new Category());
        return "catalog/init-insert";
    }

    @PostMapping("/insert-catalog")
    public String addUser(@ModelAttribute("newCat") Category newCat,
                          @RequestParam("file") MultipartFile file) throws ResourceNotFoundException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path path = Paths.get(UPLOAD_DIR + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        newCat.setPictures( "assets/images/categories/" + fileName);
        categoryRepository.save(newCat);
        return "redirect:/catalog";
    }

    @RequestMapping("/delete/{id}")
    public String deletePro(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        categoryRepository.delete(category);
        return "redirect:/catalog";
    }
}
