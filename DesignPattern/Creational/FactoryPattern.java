package DesignPattern.Creational;

import java.util.ArrayList;
import java.util.List;

public class FactoryPattern {
    static void main(String[] args) {

        Vehicle vehicle=VechileFactory.createVehicle("Bike");
        vehicle.startEngine();
        Vehicle vehicle1=VechileFactory.createVehicle("Car");
        vehicle1.startEngine();
        Vehicle vehicle2=VechileFactory.createVehicle("Truck");
        vehicle2.startEngine();
       

    }
    public static interface Vehicle{
        public void startEngine();
    }
    public static class Bike implements Vehicle{
        @Override
        public void startEngine() {
            System.out.println("Bike is starting");
        }
    }
    public static class Car implements Vehicle{
        @Override
        public void startEngine() {
            System.out.println("Car is starting");
        }
    }
    public static class VechileFactory{
        public static Vehicle createVehicle(String type) throws RuntimeException{
            if(type==null){
                throw new RuntimeException("Type cannot be Empty");
            }
            switch (type){
                case "Bike":
                    return new Bike();
                case "Car":
                    return new Car();
                default:
                    throw new RuntimeException("Vehicle Type not found");
            }

        }
    }

}
