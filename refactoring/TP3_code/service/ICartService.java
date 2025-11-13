package service;

import model.Item;
import model.CartItem;
import java.util.List;
public interface ICartService {
    void clearCart();
    void addItem(Item item);
    void addTrio(Item main, Item snack, Item drink);
    List<CartItem> getCart();
    void viewCart();
    void removeItem(int index);
    void printMenu();
}