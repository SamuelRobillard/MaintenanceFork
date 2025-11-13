package service;

public class McDonaldServiceFactory {

    public static IInventoryService createInventoryService() {
        return new InventoryService();
    }

    public static ICartService createCartService() {
        return new CartService();
    }

    public static IOrderService createOrderService(ICartService cartService) {
        return new OrderService(cartService);
    }
}
