package ui;

import service.CartService;
import service.OrderService;
import app.McDonaldSystem;

public class ClientMode {

    private final McDonaldSystem app;

    public ClientMode(McDonaldSystem app) {
        this.app = app;
    }

    public void start() {
        System.out.print("Nom: ");
        String clientName = app.readLine();
        System.out.println("Bienvenue " + clientName);

        CartService.clear();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== MENU CLIENT ===");
            System.out.println("1. Voir menu");
            System.out.println("2. Ajouter TRIO");
            System.out.println("3. Ajouter item individuel");
            System.out.println("4. Voir panier");
            System.out.println("5. Retirer du panier");
            System.out.println("6. Passer commande");
            System.out.println("7. Retour");
            System.out.print("Choix: ");

            int menuChoice = 0;
            try {
                menuChoice = app.readInt();
            } catch (Exception e) {
                System.out.println("Entrée invalide, veuillez entrer un nombre.");
                continue;
            }

            switch (menuChoice) {
                case 1 -> CartService.printMenu();
                case 2 -> CartService.addTrio(app);
                case 3 -> CartService.addItem(app);
                case 4 -> CartService.viewCart();
                case 5 -> CartService.removeItem(app);
                case 6 -> OrderService.placeOrder();
                case 7 -> isRunning = false;
                default -> System.out.println("Choix invalide !");
            }
        }
    }
}
