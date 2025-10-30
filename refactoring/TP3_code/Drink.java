public class Drink extends Item{
    public String name;
    public double price;
    public int stock;
    public String type; // "main", "snack", "drink"
    public String size; // pour drinks seulement

    public Drink(String n, double p, int s, String size) {
        super(n,p,s, "drink");
        this.size = size;
    }


}