package DesignPattern.Behavioral;

import java.util.ArrayList;
import java.util.List;

public  class ObserverPattern {
    // pub-sub systems is best example
    // Observer static interface
    public static interface OrderObserver {
        void onOrderStatusChanged(String orderId, String newStatus);
    }

    // Subject
    public static class Order {
        private String orderId;
        private String status;
        private final List<OrderObserver> observers = new ArrayList<>();

        public Order(String orderId) {
            this.orderId = orderId;
        }

        public void addObserver(OrderObserver observer) {
            observers.add(observer);
        }

        public void removeObserver(OrderObserver observer) {
            observers.remove(observer);
        }

        public void setStatus(String status) {
            this.status = status;
            notifyObservers();
        }

        private void notifyObservers() {
            for (OrderObserver observer : observers) {
                observer.onOrderStatusChanged(orderId, status);
            }
        }
    }

    // Concrete observers
    public static class SmsNotifier implements OrderObserver {
        @Override
        public void onOrderStatusChanged(String orderId, String newStatus) {
            System.out.println("SMS: Order " + orderId + " is now " + newStatus);
        }
    }

    public static class EmailNotifier implements OrderObserver {
        @Override
        public void onOrderStatusChanged(String orderId, String newStatus) {
            System.out.println("Email: Order " + orderId + " status update -> " + newStatus);
        }
    }

    public static class AnalyticsTracker implements OrderObserver {
        @Override
        public void onOrderStatusChanged(String orderId, String newStatus) {
            System.out.println("Analytics: logging status change for " + orderId);
        }
    }

    // Usage

        public static void main(String[] args) {
            Order order = new Order("ORD1001");
            order.addObserver(new SmsNotifier());
            order.addObserver(new EmailNotifier());
            order.addObserver(new AnalyticsTracker());

            order.setStatus("SHIPPED"); // all three observers fire automatically
        }
    
}
