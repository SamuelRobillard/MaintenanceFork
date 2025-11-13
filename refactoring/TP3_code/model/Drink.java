package model;

public class Drink extends Item {
    public String size;

    public Drink(String name, double price, int stock, String size) {
        super(name, price, stock, "drink");
        this.size = size;
    }
}
