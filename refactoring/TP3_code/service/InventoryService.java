package service;

import model.*;
import java.util.*;

public class InventoryService {
    public static final List<Item> inventory = new ArrayList<>();

    public static void addDefaultItems() {
        inventory.add(new MainFood("Big Mac", 6.99, 50));
        inventory.add(new MainFood("Quarter Pounder", 7.49, 40));
        inventory.add(new MainFood("McChicken", 5.99, 45));
        inventory.add(new Snack("Frites", 3.49, 100));
        inventory.add(new Snack("Nuggets (6)", 4.99, 60));
        inventory.add(new Drink("Coca-Cola", 2.49, 80, "Medium"));
        inventory.add(new Drink("Sprite", 2.49, 70, "Medium"));
        inventory.add(new Drink("Jus d'orange", 2.99, 50, "Medium"));
    }

    public static List<Item> getByType(String type) {
        List<Item> list = new ArrayList<>();
        for (Item i : inventory) {
            if (i.type.equals(type)) list.add(i);
        }
        return list;
    }
}
