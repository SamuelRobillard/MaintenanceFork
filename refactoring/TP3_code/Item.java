public class Item {
    public String name;
    public double price;
    public int stock;
    public String type; // "main", "snack", "drink"



    public Item(String n, double p, int s, String t) {
        name = n;
        price = p;
        stock = s;
        type = t;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", type='" + type + '\'' +
                '}';
    }
}
