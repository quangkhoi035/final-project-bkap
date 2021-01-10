package com.khoithuong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int price;
    private int salePrice;
    private int discount;
    private int stock;
    private String pictures;
    private String shortDetails;
    private String description;
    private String brand;
    private boolean newPro;
    private boolean sale;
    private String state;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<CartItem> listCartItem;

    public Product() {
    }

    public Product(int id, String name, int price, int salePrice, int discount, int stock, String pictures, String shortDetails, String description, String brand, boolean newPro, boolean sale, String state, Category category, List<CartItem> listCartItem) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.salePrice = salePrice;
        this.discount = discount;
        this.stock = stock;
        this.pictures = pictures;
        this.shortDetails = shortDetails;
        this.description = description;
        this.brand = brand;
        this.newPro = newPro;
        this.sale = sale;
        this.state = state;
        this.category = category;
        this.listCartItem = listCartItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public String getShortDetails() {
        return shortDetails;
    }

    public void setShortDetails(String shortDetails) {
        this.shortDetails = shortDetails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isNewPro() {
        return newPro;
    }

    public void setNewPro(boolean newPro) {
        this.newPro = newPro;
    }

    public boolean isSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<CartItem> getListCartItem() {
        return listCartItem;
    }

    public void setListCartItem(List<CartItem> listCartItem) {
        this.listCartItem = listCartItem;
    }
}
