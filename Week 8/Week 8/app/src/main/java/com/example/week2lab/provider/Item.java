package com.example.week2lab.provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {

    public static final String TABLE_NAME = "items";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "itemId")
    private int id;

    @ColumnInfo(name = "itemName")
    private String name;

    @ColumnInfo(name = "itemCost")
    private double cost;

    @ColumnInfo(name = "itemQty")
    private int quantity;

    @ColumnInfo(name = "itemFreeze")
    private boolean freeze;

    @ColumnInfo(name = "itemDesc")
    private String description;

    public Item(String name, double cost, int quantity, boolean freeze, String description) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
        this.freeze = freeze;
        this.description = description;
    }

    public int getId() {return id;}

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

    public void setId(@NonNull int id){
        this.id = id;
    }
}
