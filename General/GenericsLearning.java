package General;

import java.util.ArrayList;
import java.util.List;

public class GenericsLearning{
    static void main(String[] args){


        Cage<Human>humanCage=new Cage<Human>();
        humanCage.occupant=new Human();
        System.out.println(humanCage.getOccupant());


        //AnimalCage<Human>humanAnimalCage=new AnimalCage<Human>();//compile time error
        AnimalCage<Puppy> puppyCage=new AnimalCage<Puppy>();
        puppyCage.occupant=new Puppy();
        System.out.println(puppyCage.getOccupant());

        puppyCage.makeSound();

    }
    public static class Animal {
        void makeSound(){
            System.out.println("Animal Sound");
        }
    }
    public static class Dog extends Animal {
        void makeSound(){
            System.out.println("Dog Sound");
        }
    }
    public static class Puppy extends Dog {
        void makeSound(){
            System.out.println("Puppy Sound");
        }
    }
    public static class Cat extends Animal {
        void makeSound(){
            System.out.println("Cat Sound");
        }
    }
    public static class Human{
        void makeSound(){
            System.out.println("Human Sound");
        }
    }
    public static class Cage<Q>{
        Q occupant;
        Q getOccupant(){
            return occupant;
        }
    }
    public static class AnimalCage<X extends Animal>{
        X occupant;
        X getOccupant(){
            return occupant;
        }
        void makeSound(){
            occupant.makeSound();
        }
    }

}

