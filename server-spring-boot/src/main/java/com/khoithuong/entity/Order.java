package com.khoithuong.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "order")
    private List<CartItem> listCartItem;
    @Column(name = "amount")
    private int amount;

    public Order() {
    }

    public Order(int id, List<CartItem> listCartItem, int amount) {
        this.id = id;
        this.listCartItem = listCartItem;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartItem> getListCartItem() {
        return listCartItem;
    }

    public void setListCartItem(List<CartItem> listCartItem) {
        this.listCartItem = listCartItem;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
