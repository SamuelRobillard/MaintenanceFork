package service;

import model.CartItem;
import model.Item;

import java.util.List;

// ===================
// ORDER SERVICE
// ===================
public class OrderService implements IOrderService {

    private final ICartService cartService;

    public OrderService(ICartService cartService) {
        this.cartService = cartService;
    }



    public boolean isStockSufficient(List<CartItem> listCartItem){
        boolean isStockSufficient = true;
        // Vérification du stock pour chaque item du panier
        for (CartItem cartItem : listCartItem) {
            if (cartItem.isTrio()) {
                if (cartItem.getItem().getStock() <= 0 ||
                        cartItem.getTrioSnack().getStock() <= 0 ||
                        cartItem.getTrioDrink().getStock() <= 0) {
                    isStockSufficient = false;
                    System.out.println("Stock insuffisant pour " + cartItem.getDescription());
                }
            } else if (cartItem.getItem().getStock() <= 0) {
                isStockSufficient = false;
                System.out.println("Stock insuffisant pour " + cartItem.getItem().getName());
            }
        }

        return isStockSufficient;
    }


    public void showReceipt(List<CartItem> listCartItem){

        System.out.println("\n========= REÇU =========");
       double totalPrice = getTotalOfCartItem(listCartItem);
        removeStockFromInventory(listCartItem);

        System.out.printf("------------------------\nTOTAL: %.2f$\n", totalPrice);
        System.out.println("========================");

    }

    public double getTotalOfCartItem(List<CartItem> listCartItem){
         double totalPrice = 0;
        for (CartItem cartItem : listCartItem) {
            totalPrice += cartItem.getPrice();
        }

        return totalPrice;
    }

    public void removeStockFromInventory(List<CartItem> listCartItem){
        for (CartItem cartItem : listCartItem) {


            try {
                if (cartItem.isTrio()) {
                    cartItem.getItem().setStock(cartItem.getItem().getStock() - 1);
                    cartItem.getTrioSnack().setStock(cartItem.getTrioSnack().getStock() - 1);
                    cartItem.getTrioDrink().setStock(cartItem.getTrioDrink().getStock() - 1);
                } else {
                    cartItem.getItem().setStock(cartItem.getItem().getStock() - 1);
                }
                System.out.printf("%s - %.2f$\n", cartItem.getDescription(), cartItem.getPrice());

            } catch (Exception e) {
                System.out.println("Erreur lors de la mise à jour du stock ou de l'affichage d'un item: " + e.getMessage());
            }

        }

    }

    @Override
    public void placeOrder() {
        try {
            if (cartService.getCart().isEmpty()) {
                System.out.println("\nPanier vide !");
                return;
            }

            if(isStockSufficient(cartService.getCart())){


                // Décrémentation du stock et affichage du reçu

                showReceipt(cartService.getCart());




                // Vider le panier après la commande
                cartService.clearCart();
            }








        } catch (Exception e) {
            System.out.println("Erreur lors du traitement de la commande: " + e.getMessage());
        }
    }
}
