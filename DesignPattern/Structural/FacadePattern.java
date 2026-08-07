package DesignPattern.Structural;

public  class FacadePattern {

    public static class InventoryService {
        public boolean checkStock(String productId, int qty) {
            System.out.println("Checking stock for " + productId);
            return true;
        }
        public void reduceStock(String productId, int qty) {
            System.out.println("Reducing stock for " + productId);
        }
    }

    public static class PaymentService {
        public boolean charge(String customerId, double amount) {
            System.out.println("Charging ₹" + amount + " to " + customerId);
            return true;
        }
    }

    public static class InvoiceService {
        public String generateInvoice(String orderId) {
            System.out.println("Generating invoice for " + orderId);
            return "INV-" + orderId;
        }
    }

    public static class NotificationService {
        public void sendConfirmation(String customerId, String orderId) {
            System.out.println("Notifying " + customerId + " about order " + orderId);
        }
    }

    // Facade — hides the orchestration complexity
    public static class OrderFacade {
        private final InventoryService inventoryService = new InventoryService();
        private final PaymentService paymentService = new PaymentService();
        private final InvoiceService invoiceService = new InvoiceService();
        private final NotificationService notificationService = new NotificationService();

        public void placeOrder(String customerId, String orderId, String productId, int qty, double amount) {
            if (!inventoryService.checkStock(productId, qty)) {
                throw new RuntimeException("Out of stock");
            }
            if (!paymentService.charge(customerId, amount)) {
                throw new RuntimeException("Payment failed");
            }
            inventoryService.reduceStock(productId, qty);
            invoiceService.generateInvoice(orderId);
            notificationService.sendConfirmation(customerId, orderId);
        }
    }


    static void main(String[] args){
        OrderFacade facade = new OrderFacade();
        facade.placeOrder("cust123", "order456", "prod789", 2, 1499.0);
    }
}
