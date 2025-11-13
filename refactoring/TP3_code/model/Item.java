package model;

public class Item {
    protected String name;
    protected double price;
    protected int stock;
    protected String type;

    public Item(String name, double price, int stock, String type) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getType() { return type; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setType(String type) { this.type = type; }

    // Méthodes utilitaires pour stock
    public void addStock(int qty) {
        if (qty > 0) stock += qty;
    }

    public void removeStock(int qty) {
        if (qty > 0 && stock >= qty) stock -= qty;
    }
}
