public class Snack extends Item{
    public String name;
    public double price;
    public int stock;
    public String type; // "main", "snack", "drink"

    
    public Snack(String n, double p, int s) {
        super(n,p,s, "snack");

    }
    

}