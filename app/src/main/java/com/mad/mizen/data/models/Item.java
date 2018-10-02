package com.mad.mizen.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

@Entity
public class Item {

    @PrimaryKey
    private int itemId;

    private String name;
    private String description;
    private String category;
    private int quantity;
    private double price;

    private boolean isOrdered;

    public Item(int itemId, String name, String description, String category, int quantity, double price) {
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

    public String getCategory() {
        return category;
    }

    public int getItemId() {
        return itemId;
    }

    public boolean getOrdered() { return isOrdered; }

    public void setIsOrdered(boolean isOrdered) { this.isOrdered = isOrdered; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}

