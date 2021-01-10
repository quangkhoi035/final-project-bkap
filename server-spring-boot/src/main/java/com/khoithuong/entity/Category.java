package com.khoithuong.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String pictures;
    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="parent_id")
    private Category parentCategory;
    @JsonIgnore
    @OneToMany(mappedBy="parentCategory")
    private Set<Category> subCategory = new HashSet<Category>();

    @OneToMany(mappedBy = "category")
    private List<Product> listProduct;

    public Category() {
    }

    public Category(int id, String name, Category parentCategory, Set<Category> subCategory, String pictures) {
        this.id = id;
        this.name = name;
        this.parentCategory = parentCategory;
        this.subCategory = subCategory;
        this.pictures = pictures;
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

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public Set<Category> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Set<Category> subCategory) {
        this.subCategory = subCategory;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }
}
