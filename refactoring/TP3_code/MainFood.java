public class MainFood extends Item{
    public String name;
    public double price;
    public int stock;
    public String type; // "main", "snack", "drink"


    public MainFood(String name, double price, int numberInstock) {
        super(name,price,numberInstock, "main");

    }

    @Override
    public String toString() {
       return "name - " + this.name  + "price : "  + this.price + "$";
    }

    public String afficheSomeInfo() {
        return "name - " + this.name  + "price : "  + this.price + "$";
    }
}