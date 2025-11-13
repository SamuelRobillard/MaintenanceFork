package model;

public class Item {
    public String name;
    public double price;
    public int stock;
    public String type;

    public Item(String name, double price, int stock, String type) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }
}
