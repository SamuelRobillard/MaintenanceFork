package service;

import model.Item;
import model.CartItem;
import java.util.List;

public interface IInventoryService {
    List<Item> getAllItems();
    void addDefaultItems();
    List<Item> getByType(String type);
    void printInventory();
    void addItem(Item item);
    void addStock(String name, int quantity);
    void removeStock(String name, int quantity);
}

