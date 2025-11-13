package model;

public class CartItem {

    private final boolean isTrio;
    private final Item item;
    private final Item trioSnack;
    private final Item trioDrink;

    // Constructeur pour item individuel
    public CartItem(Item item) {
        this.item = item;
        this.trioSnack = null;
        this.trioDrink = null;
        this.isTrio = false;
    }

    // Constructeur pour trio
    public CartItem(Item main, Item snack, Item drink) {
        this.item = main;
        this.trioSnack = snack;
        this.trioDrink = drink;
        this.isTrio = true;
    }

    // Getters
    public boolean isTrio() {
        return isTrio;
    }

    public Item getItem() {
        return item;
    }

    public Item getTrioSnack() {
        return trioSnack;
    }

    public Item getTrioDrink() {
        return trioDrink;
    }

    public double getPrice() {
        if (isTrio) {
            return item.getPrice() + trioSnack.getPrice() + trioDrink.getPrice();
        }
        return item.getPrice();
    }

    public String getDescription() {
        if (isTrio) {
            return item.getName() + " + " + trioSnack.getName() + " + " + trioDrink.getName();
        }
        return item.getName();
    }
}
