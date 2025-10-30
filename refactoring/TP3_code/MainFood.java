public class MainFood extends Item{
    public String name;
    public double price;
    public int stock;
    public String type; // "main", "snack", "drink"


    public MainFood(String n, double p, int s) {
        super(n,p,s, "main");

    }


}