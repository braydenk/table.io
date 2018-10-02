package com.mad.mizen.data.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import java.util.ArrayList;
import java.util.List;


// TODO: Figure out how to use array/list
@Entity
public class Order {

    @PrimaryKey
    private int orderId;

    @Ignore
    private List<Item> items;

    private int status;
    private double totalPrice;

    public Order(int orderId, int status, double totalPrice) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getStatus() {
        return status;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
