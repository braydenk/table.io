package com.mad.mizen.data.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Order {

    @PrimaryKey
    private int orderId;

    private int tableId;
    @Embedded
    private Item[] items;
    private int status;
    private double totalPrice;

    public Order(int orderId, Item[] items, int status, double totalPrice) {
        this.orderId = orderId;
        this.items = items;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public int getTableId() {
        return tableId;
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

    public Item[] getItems() {
        return items;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public void setItems(Item[] items) {
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
