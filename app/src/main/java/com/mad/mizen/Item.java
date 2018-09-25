package com.mad.mizen;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Item {

    @PrimaryKey
    private int itemId;

    private String name;
    private String description;
    private int category;
    private int quantity;
    private double price;

    public Item(int itemId, int name, String description, int category, int quantity, double price) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public int getCategory() {
        return category;
    }

    public int getItemId() {
        return itemId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}

