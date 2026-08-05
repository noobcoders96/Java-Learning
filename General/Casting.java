package General;

import java.io.InputStreamReader;

public class Casting {
    public static void main(String[] args){
        System.out.println();
        System.out.println("Child reference:Child Object");
        Dog dog = new Dog();
       
        if(dog instanceof Animal){
            System.out.println("Yes,We can say Dog is an instance of Animal");
        }
        else{
            System.out.println("No,We can't say Dog is an instance of Animal");
        }
        dog.makeSound();
        dog.bark();
        System.out.println(dog.name);
        System.out.println("------------------------------------");

        System.out.println("Parent reference:Child Object");
        Animal animal=new Dog();
        animal.makeSound();
        //animal.bark();-->since method is found only in child it cannot be called via parent reference
        System.out.println(animal.name);
        System.out.println("-------------------------------------");

        System.out.println("------------Upcasting-----------------");
        //Upcasting ---> assigning child Object/Reference to Parent Reference
        Dog dog2=new Dog();
        Animal animal2=dog2;
        animal2.makeSound();
        System.out.println(animal2.name);
        System.out.println("--------------------------------------");

        System.out.println("------------Downcasting-----------------");
        //Downcasting ---> assigning parent refernce to Child reference
        Animal animal3=new Dog();
        Dog dog3=(Dog)animal3;
        dog3.makeSound();

        System.out.println(dog3.name);
        System.out.println("--------------------------------------");

        Animal animal4=new Animal();
        Dog dog4=null;
        if(animal4 instanceof Dog){
            dog4=(Dog)animal4;
        }else{
            throw new IllegalArgumentException("Class Casting cannot be done");
        }



    }
    public static class Animal{
        String name="Animal";
        public void makeSound(){
            System.out.println("Animal Sound");
        }
        static void hello(){
            System.out.println("Hello");
        }

    }
    public static class Dog extends Animal{

        String name="Dog";

        public void bark(){
            System.out.println("Dog Bark");
        }
        public void makeSound(){
            System.out.println("Dog Sound");
        }

    }


}

