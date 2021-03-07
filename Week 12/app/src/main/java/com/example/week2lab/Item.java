package com.example.week2lab;

public class Item {
    private String name;
    private double cost;
    private int quantity;
    private boolean freeze;
    private String description;

    public Item(String name, double cost, int quantity, boolean freeze, String description) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.freeze = freeze;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isFreeze() {
        return freeze;
    }

    public String getDescription() {
        return description;
    }
}
