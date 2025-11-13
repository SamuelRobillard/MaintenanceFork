package model;

public class CartItem {
    public Item item;
    public Item trioSnack;
    public Item trioDrink;
    public boolean isTrio;

    public CartItem(Item item) {
        this.item = item;
        this.isTrio = false;
    }

    public CartItem(Item main, Item snack, Item drink) {
        this.item = main;
        this.trioSnack = snack;
        this.trioDrink = drink;
        this.isTrio = true;
    }

    public double getPrice() {
        if (isTrio) {
            return item.price + trioSnack.price + trioDrink.price - 1.50; // petite réduction trio
        } else {
            return item.price;
        }
    }

    public String getDescription() {
        if (isTrio) {
            return item.name + " + " + trioSnack.name + " + " + trioDrink.name + " (Trio)";
        } else {
            return item.name;
        }
    }
}
