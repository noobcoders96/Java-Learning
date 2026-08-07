package DesignPattern.Behavioral;

public class StrategyPattern {

    // Strategy static interface
    public static interface DiscountStrategy {
        double applyDiscount(double amount);
    }

    // Concrete strategies
    public static class NoDiscountStrategy implements DiscountStrategy {
        public double applyDiscount(double amount) {
            return amount;
        }
    }

    public static class PremiumMemberDiscount implements DiscountStrategy {
        public double applyDiscount(double amount) {
            return amount * 0.85; // 15% off
        }
    }

    public static class FestiveSaleDiscount implements DiscountStrategy {
        public double applyDiscount(double amount) {
            return amount - 200; // flat ₹200 off
        }
    }

    // Context — uses a strategy, doesn't care which
    public static class ShoppingCart {
        private DiscountStrategy discountStrategy;

        public ShoppingCart(DiscountStrategy discountStrategy) {
            this.discountStrategy = discountStrategy;
        }

        public void setDiscountStrategy(DiscountStrategy discountStrategy) {
            this.discountStrategy = discountStrategy; // swap at runtime
        }

        public double checkout(double totalAmount) {
            return discountStrategy.applyDiscount(totalAmount);
        }
    }

    // Usage
        public static void main(String[] args) {
            ShoppingCart cart = new ShoppingCart(new NoDiscountStrategy());
            System.out.println(cart.checkout(1000)); // 1000.0

            cart.setDiscountStrategy(new PremiumMemberDiscount());
            System.out.println(cart.checkout(1000)); // 850.0

            cart.setDiscountStrategy(new FestiveSaleDiscount());
            System.out.println(cart.checkout(1000)); // 800.0
        }
    
}
